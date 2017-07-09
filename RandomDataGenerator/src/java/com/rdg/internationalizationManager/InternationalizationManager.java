package com.rdg.internationalizationManager;

import com.rdg.constants.Const;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

/*
* Copyright 2014 Radislav Tapavicki <radislavtt@gmail.com>
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

/**
 * This class provides internationalization support
 */
public class InternationalizationManager {
    final static Logger logger = Logger.getLogger(InternationalizationManager.class);
    private static Properties properties = new Properties();

    /**
     * Add a language file to the internationalization database
     *
     * @param prefix
     * @throws Exception
     */
    public static void addLanguageFile(String prefix) throws Exception {
        URL url = InternationalizationManager.class.getResource(Const.SLASH + prefix + Const.DOT_DELIMITER + Locale.getDefault().toString() + Const.DOT_DELIMITER + Const.PROPERTIES);
        try {
            properties.load(url.openStream());
        } catch (IOException e) {
            logger.debug(InternationalizationManager.getString(Const.ERROR_LOADING_INTERNATIONALIZATION_FILE) + Const.NEW_LINE, e);
            throw new Exception(InternationalizationManager.getString(Const.ERROR_LOADING_INTERNATIONALIZATION_FILE) + Const.NEW_LINE, e);
        }
    }

    /**
     * Get the internationalized string for identifier
     *
     * @param identifier
     * @return String identifier
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
