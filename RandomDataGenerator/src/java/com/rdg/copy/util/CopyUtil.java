package com.rdg.copy.util;

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

public class CopyUtil {
    final static Logger logger = Logger.getLogger(CopyUtil.class);

    public static Object deepCopyObject(Object object) {
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
            copyObject = new ObjectInputStream(bais).readObject();
        } catch (IOException e) {
            logger.debug(e);
        } catch (ClassNotFoundException e) {
            copyObject = null;
            logger.debug(e);
        }
        return copyObject;
    }
}
