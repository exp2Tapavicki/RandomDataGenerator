package com.rdg.json.util;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rdg.constants.Const;
import com.rdg.internationalizationManager.InternationalizationManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

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

public class JSONUtil {

    private final static Logger logger = Logger.getLogger(JSONUtil.class);
    public static ObjectMapper objectMapper = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, Boolean.TRUE)
            .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, Boolean.TRUE)
            .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, Boolean.TRUE);
    private static String FILE_NAME = "RandomDataGenerator/json/test";

    /**
     * transform object to xml
     *
     * @param object
     * @param FILE_NAME
     */
    public static void objectToJSON(Object object, String FILE_NAME) {
        JsonFactory factory = objectMapper.getJsonFactory();
        try {
            File file = new File(FILE_NAME);
            JsonGenerator gen = factory.createGenerator(file, JsonEncoding.UTF8);
            objectMapper.getSerializerProvider().setNullKeySerializer(new NullKeySerializer());
            objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
            objectMapper.writeValue(gen, object);
        } catch (IOException e) {
            logger.error(InternationalizationManager.getString(Const.ERROR_IN_JSON_WRITE) + e);
        }
    }

    /**
     * transform object to xml
     *
     * @param object
     */
    public static void objectToJSON(Object object) {
        try {
            objectMapper.readValue(new File(FILE_NAME), object.getClass());
        } catch (IOException e) {
            logger.error(Const.ERROR_IN_JSON_READ + e);
        }
    }

    /**
     * transform xml to object
     *
     * @param FILE_NAME
     * @return
     */
    public static Object JSONToObject(String FILE_NAME, Class<?> cl) {
        Object object = null;
        try {
            File file = new File(FILE_NAME);
            objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
            objectMapper.getSerializerProvider().setNullKeySerializer(new NullKeySerializer());

            if (file.exists()) {
                object = objectMapper.readValue(file, cl);
            }
        } catch (Exception e) {
            logger.error(Const.ERROR_IN_JSON_READ + e);
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
