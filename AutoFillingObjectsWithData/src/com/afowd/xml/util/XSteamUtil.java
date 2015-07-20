package com.afowd.xml.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XSteamUtil {
	private static String FILE_NAME = "xml/test";
	private static XStream xSteam = new XStream(new DomDriver("UTF-8"));
	/**
	 * transform object to xml
	 * @param object
	 * @param FILE_NAME
	 */
	public static void xSteamObjectToXML(Object object, String FILE_NAME) {
		try {
			xSteam.toXML(object, new FileWriter(FILE_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * transform object to xml
	 * @param object
	 * @param FILE_NAME
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
			Reader reader = new InputStreamReader(inputStream,"UTF-8");
			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");
			object = xSteam.fromXML(inputStream);
		} catch (Exception e) {
			System.err.println("Error in XML Read: " + e.getMessage());
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
