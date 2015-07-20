package com.afowd.copy.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

public class CopyUtil {
	final static Logger logger = Logger.getLogger(CopyUtil.class);
	public static Object deepCopyObject (Object object){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		Object copyObject = null;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			oos.flush();
			oos.close();
			bos.close();
			byte[] byteData = bos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
			copyObject = (Object) new ObjectInputStream(bais).readObject();
		} catch (IOException e) {
			logger.debug(e);
		} catch (ClassNotFoundException e) {
			copyObject = null;
			logger.debug(e);
		}
		return copyObject;
	}
	
	
//	public static Object copyObject (Object object){
//		return XSTREAM.fromXML(XSTREAM.toXML(object));
//	}
}
