package com.rdg.xml.util;

import com.rdg.constants.Const;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.log4j.Logger;

import java.io.*;

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

public class XSteamUtil {
    private final static Logger logger = Logger.getLogger(XSteamUtil.class);
    private static String FILE_NAME = "RandomDataGenerator/xml/test";
    private static XStream xSteam = new XStream(new DomDriver(Const.UTF_8));

    /**
     * transform object to xml
     *
     * @param object
     * @param FILE_NAME
     */
    public static void xSteamObjectToXML(Object object, String FILE_NAME) {
        try {
            xSteam.toXML(object, new FileWriter(FILE_NAME));
        } catch (IOException e) {
            logger.error(Const.ERROR_IN_XML_WRITE + e);
        }
    }

    /**
     * transform object to xml
     *
     * @param object
     */
    public static void xSteamObjectToXML(Object object) {
        xSteamObjectToXML(object, FILE_NAME);
    }

    /**
     * transform xml to object
     *
     * @param FILE_NAME
     * @return
     */
    public static Object xSteamXMLToObject(String FILE_NAME) {
        Object object = null;
        try {
            File xmlFile = new File(FILE_NAME);
            InputStream inputStream = new FileInputStream(xmlFile);
            Reader reader = new InputStreamReader(inputStream, Const.UTF_8);
            object = xSteam.fromXML(reader);
        } catch (Exception e) {
            logger.error(Const.ERROR_IN_XML_READ + e);
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
