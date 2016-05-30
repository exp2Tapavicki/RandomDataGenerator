package com.afowd.xml.util;

import com.afowd.constants.Const;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

import java.io.*;

public class XSteamUtil {
	private static String FILE_NAME = "AutoFillingObjectsWithData/xml/test";
	private static XStream xSteam = new XStream(new DomDriver(Const.UTF_8));
    private final static Logger logger = Logger.getLogger(XSteamUtil.class);
	/**
	 * transform object to xml
	 * @param object
	 * @param FILE_NAME
	 */
	public static void xSteamObjectToXML(Object object, String FILE_NAME) {
		try {
			xSteam.toXML(object, new FileWriter(FILE_NAME));
		} catch (IOException e) {
            logger.error("Error in XML Read: " + e);
		}
	}
	/**
	 * transform object to xml
	 * @param object
	 */
	public static void xSteamObjectToXML(Object object) {
		xSteamObjectToXML(object, FILE_NAME);
	}
	/**
	 * transform xml to object
	 * @param FILE_NAME
	 * @return
	 */
	public static Object xSteamXMLToObject(String FILE_NAME) {
		Object object = null;
		try {
			File xmlFile = new File(FILE_NAME);
			InputStream inputStream= new FileInputStream(xmlFile);
			Reader reader = new InputStreamReader(inputStream,Const.UTF_8);
			object = xSteam.fromXML(reader);
		} catch (Exception e) {
            logger.error("Error in XML Read: " + e);
		}
		return object;
	}

	public static String getFILE_NAME() {
		return FILE_NAME;
	}

	public static void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}

	
}
