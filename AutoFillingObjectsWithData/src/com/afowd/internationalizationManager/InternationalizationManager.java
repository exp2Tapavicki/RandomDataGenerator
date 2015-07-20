package com.afowd.internationalizationManager;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.afowd.constants.Const;

/**
 * This class provides internationalization support
 */
public class InternationalizationManager {
	private static Properties properties = new Properties();
	final static Logger logger = Logger.getLogger(InternationalizationManager.class);
	/**
	 * Add a language file to the internationalization database
	 * @throws Exception 
	 */
	public static void addLanguageFile(String prefix) throws Exception {
			URL url = InternationalizationManager.class.getResource("/" + prefix + "." + Locale.getDefault().toString() + ".properties");
			try {
				properties.load(url.openStream());
			} catch (IOException e) {
				logger.debug(InternationalizationManager.getString(Const.ERROR_LOADING_INTERNATIONALIZATION_FILE) + "\n", e);
				throw new Exception(InternationalizationManager.getString(Const.ERROR_LOADING_INTERNATIONALIZATION_FILE) + "\n", e);
			}
	}
	
	/**
	 * Get the internationalized string for identifier
	 */
	public static String getString(String identifier) {
		try {
			String value = properties.getProperty(identifier);
			if (value == null) {
				logger.warn(InternationalizationManager.getString(Const.ERROR_NO_INTERNATIONALIZATION_FOR_IDENTIFIER) + " [" + identifier + "]");
				return identifier;
			}
			return value;
		} catch (Exception e) {
			if (identifier == null)
				return Const.NULL;
		}
		return Const.NULL;
	}
}
