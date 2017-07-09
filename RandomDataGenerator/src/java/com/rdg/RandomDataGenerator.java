package com.rdg;

import com.rdg.boundary.BoundaryManager;
import com.rdg.constants.Const;
import com.rdg.counter.TimeCounter;
import com.rdg.internationalizationManager.InternationalizationManager;
import com.rdg.test.data.util.TestDataUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;
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

public class RandomDataGenerator {
    private final static Logger logger = Logger.getLogger(RandomDataGenerator.class);

    /**
     * @param args args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        TimeCounter timeCounter = new TimeCounter();
        initializeLogging();
        initializeBoundaryManager();
        initializeBase();

        TestDataUtil.printAndTestResultOneFilter();
        TestDataUtil.printAndTestResultMoreFilters();
        TestDataUtil.printAndTestResultSortOrder();
        TestDataUtil.exportObjectsToXML();
        TestDataUtil.XMLToObject();
        TestDataUtil.exportXMLtoXSL();
        TestDataUtil.exportObjectsToJSON();
        TestDataUtil.JSONToObject();
//      Jasper reports work at the moment on java 1.6 so it is not supported now
//		TestDataUtil.createJasperReport();
//		TestDataUtil.createJasperReportExportPDF();
//		TestDataUtil.createJasperReportExportXSL();
        logger.info(timeCounter.printUsedTime());
    }

    /**
     * Initialize log4j with a basic console appender
     *
     * @throws Exception
     */
    private static void initializeLogging() {
        URL url = RandomDataGenerator.class.getResource("/rdg/log4j.properties");
        Properties properties = new Properties();
        try {
            properties.load(url.openStream());
        } catch (IOException e) {
            logger.debug(InternationalizationManager.getString(Const.ERROR_LOADING_FILE) + Const.NEW_LINE, e);
        }
        PropertyConfigurator.configure(properties);
    }

    /**
     * Initialize boundary range for specific property
     *
     * @throws Exception
     */
    private static void initializeBoundaryManager() throws Exception {
        BoundaryManager.addBoundaryFile("rdg/boundary");
    }

    /**
     * Add the core internationalization
     */
    private static void initializeBase() throws Exception {
        InternationalizationManager.addLanguageFile("rdg/base");
    }

}
