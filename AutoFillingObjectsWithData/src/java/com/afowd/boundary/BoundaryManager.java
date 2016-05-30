package com.afowd.boundary;

import com.afowd.constants.Const;
import com.afowd.internationalizationManager.InternationalizationManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

/**
 * This class provides boundary support
 */
public class BoundaryManager {
    private static Properties properties = new Properties();
    private final static Logger logger = Logger.getLogger(BoundaryManager.class);

    /**
     * Add a file to the boundary database
     *
     * @param prefix
     * @throws Exception
     */
    public static void addBoundaryFile(String prefix) throws Exception {
        URL url = BoundaryManager.class.getResource("/" + prefix + ".properties");
        try {
            properties.load(url.openStream());
        } catch (IOException e) {
            logger.debug(InternationalizationManager.getString(Const.ERROR_LOADING_FILE) + Const.NEW_LINE, e);
            throw new Exception(InternationalizationManager.getString(Const.ERROR_LOADING_FILE) + Const.NEW_LINE, e);
        }
    }

    /**
     * Get the boundary string for identifier
     *
     * @param identifier
     * @return String
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
                return Const.NULL_STRING;
        }
        return Const.NULL_STRING;
    }

    /**
     * Get all boundaries in HashMap
     *
     * @return HashMap<String, String>
     */
    public static HashMap<String, String> getAllBoundaries() {
        HashMap<String, String> hmBoundaries = new HashMap<>();
        Set<Object> sKeys = properties.keySet();
        for (Object sKey1 : sKeys) {
            String sKey = (String) sKey1;
            hmBoundaries.put(sKey, properties.getProperty(sKey));
        }
        return hmBoundaries;
    }
}
