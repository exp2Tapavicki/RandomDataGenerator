package com.afowd.boundary;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.afowd.constants.Const;
import com.afowd.internationalizationManager.InternationalizationManager;

/**
 * This class provides boundary support
 */
public class BoundaryManager {
	private static Properties properties = new Properties();
	final static Logger logger = Logger.getLogger(BoundaryManager.class);
	/**
	 * Add a file to the boundary database
	 * @throws Exception 
	 */
	public static void addBoundaryFile(String prefix) throws Exception {
			URL url = BoundaryManager.class.getResource("/" + prefix + ".properties");
			try {
				properties.load(url.openStream());
			} catch (IOException e) {
				logger.debug(InternationalizationManager.getString(Const.ERROR_LOADING_FILE) + "\n", e);
				throw new Exception(InternationalizationManager.getString(Const.ERROR_LOADING_FILE) + "\n", e);
			}
	}
	
	/**
	 * Get the boundary string for identifier
	 */
	public static String getString(String identifier) {
		try {
			String value = properties.getProperty(identifier);
			if (value == null) {
				return null;
			}
			return value;
		} catch (Exception e) {
			if (identifier == null)
				return Const.NULL;
		}
		return Const.NULL;
	}
	
	public static HashMap<String, String> getAllBoundaries(){
		HashMap<String, String> hmBoundaries = new HashMap<String, String>();
		Set<Object> sKeys = properties.keySet();
		for (Iterator<Object> iterator = sKeys.iterator(); iterator.hasNext();) {
			String sKey = (String) iterator.next();
			hmBoundaries.put(sKey, properties.getProperty(sKey));
			
		}
		return hmBoundaries;
	}
}
