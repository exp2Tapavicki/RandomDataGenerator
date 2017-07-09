package com.rdg.util;

import com.rdg.boundary.Boundary;
import com.rdg.boundary.BoundaryManager;
import com.rdg.constants.BasicClassConstants;
import com.rdg.constants.Const;
import com.rdg.internationalizationManager.InternationalizationManager;
import com.rdg.range.filter.RangeNumberFilter;
import org.apache.log4j.Logger;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class RandomDataUtil {
    final static Logger logger = Logger.getLogger(RandomDataUtil.class);
    private static HashMap<String, ArrayList<Object>> hmFilledClasses = new HashMap<>();
    private static HashMap<Integer, HashSet<String>> hmClassesPerDeepLevel = new HashMap<>();
    private static HashSet<String> hsOrmClassesForFill = new HashSet<>();
    private static int numberOfData = Const.DEFAULT_NUMBER_OF_DATA;
    private static int numberOfDataForOneToManyRelation = Const.DEFAULT_NUMBER_OF_DATA_FOR_ONE_TO_MANY_RELATION;
    private static int deepLevel = Const.TWO;
    private static HashMap<String, Boundary> hmBoundary = new HashMap<>();
    private static boolean bAllowNulls = Boolean.TRUE;
    private static int precision = Const.FIVE;
    private static Pattern pDatePattern = Pattern.compile(RegularExpUtil.sDatePattern);
    private static Matcher matcher = null;
    private static String sPackageFromClass = null;
    private static String sPackageFromEnum = null;
    private static String sClassName = null;
    private static ThreadFactoryUtil tfu = new ThreadFactoryUtil();
    private static Runnable runnable = null;

    public static String getsClassName() {
        return sClassName;
    }

    public static void setsClassName(String sClassName) {
        RandomDataUtil.sClassName = sClassName;
    }

    public static String getsPackageFromClass() {
        return sPackageFromClass;
    }

    public static void setsPackageFromClass(String sPackageFromClass) {
        RandomDataUtil.sPackageFromClass = sPackageFromClass;
    }

    public static String getsPackageFromEnum() {
        return sPackageFromEnum;
    }

    public static void setsPackageFromEnum(String sPackageFromEnum) {
        RandomDataUtil.sPackageFromEnum = sPackageFromEnum;
    }

    public static int getPrecision() {
        return precision;
    }

    public static void setPrecision(int precision) {
        RandomDataUtil.precision = precision;
    }

    public static boolean isbAllowNulls() {
        return bAllowNulls;
    }

    public static void setbAllowNulls(boolean bAllowNulls) {
        RandomDataUtil.bAllowNulls = bAllowNulls;
    }

    public static int getNumberOfData() {
        return numberOfData;
    }

    public static void setNumberOfData(int numberOfData) {
        RandomDataUtil.numberOfData = numberOfData;
    }

    public static int getNumberOfDataForOneToManyRelation() {
        return numberOfDataForOneToManyRelation;
    }

    public static void setNumberOfDataForOneToManyRelation(int numberOfDataForOneToManyRelation) {
        RandomDataUtil.numberOfDataForOneToManyRelation = numberOfDataForOneToManyRelation;
    }

    public static int getDeepLevel() {
        return deepLevel;
    }

    public static void setDeepLevel(int deepLevel) {
        RandomDataUtil.deepLevel = deepLevel;
    }

    public static HashMap<String, Boundary> getHmBoundary() {
        return hmBoundary;
    }

    public static void setHmBoundary(HashMap<String, Boundary> hmBoundary) {
        RandomDataUtil.hmBoundary = hmBoundary;
    }

    /**
     * return random generated RangeNumberFilter
     *
     * @return
     */
    public static RangeNumberFilter getRandomRangeNumberFilter(String sClassName, String sProperty) {
        RangeNumberFilter rangeNumberFilter = new RangeNumberFilter();
        rangeNumberFilter.setRangeFrom(RandomUtil.getRandomLong(Long.MIN_VALUE, Long.MAX_VALUE, Boolean.FALSE));
        rangeNumberFilter.setRangeTo(RandomUtil.getRandomLong(Long.MIN_VALUE, Long.MAX_VALUE, Boolean.FALSE));
        rangeNumberFilter.setInRange(RandomUtil.getRandomBoolean(Boolean.FALSE));
        rangeNumberFilter.setNameOfTheClass(sClassName);
        rangeNumberFilter.setProperty(sProperty);
        return rangeNumberFilter;
    }

    /**
     * Fill data of specific Class, SubClasses example ArrayList etc who have actual arguments type ArrayList<Job>
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Object> getFilledData() {
        hmFilledClasses = new HashMap<>();
        tfu = new ThreadFactoryUtil();
        tfu.setDaemon(true);
        tfu.setPriority(Thread.NORM_PRIORITY);
        tfu.setStackSize(1024);
        tfu.setThreadGroup(new ThreadGroup("ClassesForFill"));
        tfu.setWrapRunnable(true);

        if (sClassName != null) {
            findOutBoundaries();
            getFilledBasicData(sClassName);

            if (deepLevel > Const.ZERO) {
                tfu.setiPriorityThreadLevel(Const.ZERO);
                getFilledBasicDataForRelationClasses(sClassName);
                tfu.setiPriorityThreadLevel(Const.ONE_INTEGER);
                getFilledRelationsForClasses();
                HashMap<Integer, ArrayList<Thread>> hmThreads = tfu.getHmThreads();

                for (Entry<Integer, ArrayList<Thread>> entry : hmThreads.entrySet()) {
                    ArrayList<Thread> alThreads = entry.getValue();
                    alThreads.forEach(Thread::start);
                    for (Thread thread : alThreads) {
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            logger.error(e);
                        }
                    }
                }
            }
            if (hmFilledClasses.get(sClassName) != null && !hmFilledClasses.get(sClassName).isEmpty()) {
                return hmFilledClasses.get(sClassName);
            } else {
                logger.error(InternationalizationManager.getString(Const.CLASS_NOT_EXIST) + "[" + sClassName + "] " + InternationalizationManager.getString(Const.IN_METHOD) + " getFilledData(String sClassName)");
                return null;
            }
        } else {
            logger.error(InternationalizationManager.getString(Const.CLASS_NOT_EXIST) + "[" + sClassName + "] " + InternationalizationManager.getString(Const.IN_METHOD) + " getFilledData(String sClassName)");
            return null;
        }
    }

    /**
     * loads Boundaries from properties file or find out max min values for types and put it in hash map for lather use
     */
    private static void findOutBoundaries() {
        HashMap<String, String> hmTemp = BoundaryManager.getAllBoundaries();
        Iterator<Map.Entry<String, String>> iterator = hmTemp.entrySet().iterator();
        Boundary boundary;
        String sKey;
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            sKey = entry.getKey().substring(Const.ZERO, entry.getKey().lastIndexOf(Const.DOT_DELIMITER));
            if (getHmBoundary().containsKey(sKey)) {
                boundary = getHmBoundary().get(sKey);
            } else {
                boundary = new Boundary();
                getHmBoundary().put(sKey, boundary);
            }
            String[] sClassPropertySplitted = entry.getKey().split(RegularExpUtil.MATCH_DOT);
            String sClassName = sClassPropertySplitted[Const.ZERO];
            String sClassProperty = sClassPropertySplitted[Const.ONE];
            String sPropertyValues = entry.getValue();
            String sReturnType = getReturnParamFromMethod(sClassName, sClassProperty).getName();
            String sLast = sClassPropertySplitted[sClassPropertySplitted.length - Const.ONE];
            switch (sLast) {
                case Const.SUFFIX_MIN:
                    boundary.setObjMin(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues.trim())));
                    break;
                case Const.SUFFIX_MAX:
                    boundary.setObjMax(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues.trim())));
                    break;
                case Const.ALLOW_NULLS:
                    boundary.setbAllowNulls(Boolean.valueOf(Boolean.valueOf(sPropertyValues.trim().toString()).booleanValue()));
                    break;
                case Const.SUFFIX_PRECISION:
                    boundary.setObjPrecision(getDinamiclyCastWithValue(int.class.getName(), checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues.trim())));
                    break;
                case Const.SUFFIX_ENUM:
                    boundary.setObjEnum((String[]) getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues.trim())));
                    break;
            }
        }
        List<String> lBasicType = RandomUtil.listOfBasicTypes;
        for (String sBasicType : lBasicType) {
            boundary = new Boundary();
            boundary.setbAllowNulls(bAllowNulls);
            boundary.setObjPrecision(precision);
            getMinimumMaximumLimitForClass(sBasicType, boundary);
            if (!RandomUtil.mMappingMap.containsKey(sBasicType)) {
                getHmBoundary().put(sBasicType, boundary);
            } else {
                getHmBoundary().put(sBasicType, boundary);
            }
        }
    }

    /**
     * fill all relation for classes
     */
    private static void getFilledRelationsForClasses() {
        for (final String sClasses : hsOrmClassesForFill) {
            runnable = () -> getFilledRelationsForData(sClasses);
            tfu.setsClassName(sClasses);
            tfu.newThread(runnable);
        }
    }

    /**
     * Fill basic data of specific Class (int, float, char, Integer, Bigdecimal, String...)
     *
     * @param sClassName
     * @return
     */
    public static void getFilledBasicData(String sClassName) {
        for (int i = Const.ZERO; i < getTempNumberOfData(sClassName); i++) {
            Object obj = getClassInstance(sClassName);
            Method[] methods = getClassMethods(sClassName);
            if (obj != null && methods != null && methods.length >= Const.ONE) {
                Object object = getFilledBasicData(obj, methods, i);
                fillHashMapWithObjects(sClassName, object);
            }
        }
    }

    /**
     * Fill relation classes with basic data
     *
     * @param sClassName
     */
    public static void getFilledBasicDataForRelationClasses(String sClassName) {
        HashSet<String> hsLevelFill = new HashSet<>();
        hsOrmClassesForFill.add(sClassName);
        hsLevelFill.add(sClassName);
        hmClassesPerDeepLevel.put(Integer.valueOf(Const.ZERO_INTEGER), hsLevelFill);
        for (int i = Const.ZERO; i < deepLevel; i++) {
            hsLevelFill = new HashSet<>();
            for (String sClassNameForDeep : hmClassesPerDeepLevel.get(Integer.valueOf(i))) {
                getAllClassesForRelationsFill(sClassNameForDeep, hsLevelFill);
            }
            hmClassesPerDeepLevel.put(Integer.valueOf(i + Const.ONE), hsLevelFill);
        }
        for (final String sClassesName : hsOrmClassesForFill) {
            if (!hmFilledClasses.containsKey(sClassesName)) {
                runnable = () -> {
                    for (int i = Const.ZERO; i < numberOfDataForOneToManyRelation; i++) {
                        Object obj = getClassInstance(sClassesName);
                        Method[] methods = getClassMethods(sClassesName);
                        if (obj != null && methods != null && methods.length >= Const.ONE) {
                            fillHashMapWithObjects(sClassesName, getFilledBasicData(obj, methods, i));
                        }
                    }
                };
                tfu.setsClassName(sClassesName);
                tfu.newThread(runnable);
            }
        }
    }

    /**
     * Fill relation of basic class with orm classes
     *
     * @param sClassName
     */
    public static void getFilledRelationsForData(String sClassName) {
        ArrayList<Object> alData = hmFilledClasses.get(sClassName);
        if (alData != null && !alData.isEmpty()) {
            for (int i = Const.ZERO; i < getTempNumberOfData(sClassName); i++) {
                Object object = alData.get(i);
                Method[] methods = object.getClass().getMethods();
                if (object != null && methods != null && methods.length >= Const.ONE) {
                    for (Method method : methods) {
                        String sMethodName = method.getName();
                        Object objectForSet;
                        if (sMethodName.toLowerCase().startsWith(Const.STRING_SET)) {
                            String sParameterType = getFirstMethodParameterType(method);
                            if (hmFilledClasses.containsKey(sParameterType)) {
                                objectForSet = hmFilledClasses.get(sParameterType).get(RandomUtil.getRandomPrimitiveInt(Const.ZERO, getTempNumberOfData(sParameterType) - Const.ONE));
                                invokeMethodOnObject(object, method, objectForSet);
                            } else if (RandomUtil.listOfArrayTypes.contains(sParameterType)) {
                                if (!RandomUtil.listOfBasicTypes.contains(getFirstActualTypeArgumentClass(method.getGenericParameterTypes())) &&
                                        !RandomUtil.listOfArrayTypes.contains(getFirstActualTypeArgumentClass(method.getGenericParameterTypes())) &&
                                        !RandomUtil.listOfHashTypes.contains(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()))) {
                                    objectForSet = getRandomDataOfFilledClasses(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()));
                                    invokeMethodOnObject(object, method, objectForSet);
                                }
                            } else if (RandomUtil.listOfHashTypes.contains(sParameterType)) {
                                Vector<Class<?>> vectorParams = getHashActualTypeArgumentsClass(method.getGenericParameterTypes());
                                if (vectorParams != null) {
                                    if (vectorParams != null && !vectorParams.isEmpty()) {
                                        Vector<Object> vector;
                                        Object tempObject = null;
                                        for (int k = Const.ZERO; k < getTempNumberOfData(sParameterType); k++) {
                                            vector = new Vector<>();
                                            for (Class<?> vectorParam : vectorParams) {
                                                String sParam = vectorParam.getName();
                                                if (RandomUtil.listOfBasicTypes.contains(sParam)) {
                                                    vector.add(getRandomBasicObjectValue(sParam, null, sClassName));
                                                } else {
                                                    vector.add(hmFilledClasses.get(sParam).get(RandomUtil.getRandomPrimitiveInt(Const.ZERO, getTempNumberOfData(sParam) - Const.ONE)));
                                                }
                                            }
                                            if (vectorParams.size() > 1) {
                                                if (tempObject == null) {
                                                    tempObject = new HashMap<>();
                                                }
                                                ((HashMap) tempObject).put(vector.get(Const.ZERO), vector.get(Const.ONE));
                                            } else {
                                                if (tempObject == null) {
                                                    tempObject = new HashSet<>();
                                                }
                                                ((HashSet) tempObject).add(vector.get(Const.ZERO));
                                            }
                                        }
                                        invokeMethodOnObject(object, method, tempObject);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * get random number of filled class
     *
     * @param sParameterType
     * @return
     */
    private static ArrayList<Object> getRandomDataOfFilledClasses(String sParameterType) {
        ArrayList<Object> alData = new ArrayList<>();
        for (int i = Const.ZERO; i < getTempNumberOfData(sParameterType); i++) {
            alData.add(hmFilledClasses.get(sParameterType).get(RandomUtil.getRandomPrimitiveInt(Const.ZERO, getTempNumberOfData(sParameterType) - Const.ONE)));
        }
        return alData;
    }

    /**
     * get actual number of current data
     *
     * @param sClassName
     * @return
     */
    private static int getTempNumberOfData(String sClassName) {
        if (sClassName.equals(RandomDataUtil.sClassName)) {
            return numberOfData;
        } else {
            return numberOfDataForOneToManyRelation;
        }
    }

    /**
     * get all classes for filling relations
     *
     * @param sClassName
     * @param hsLevelFill HashSet of names of classes to fill
     */
    private static void getAllClassesForRelationsFill(String sClassName, HashSet<String> hsLevelFill) {
        Object obj = getClassInstance(sClassName);
        Method[] methods = getClassMethods(sClassName);
        if (obj != null && methods != null && methods.length >= Const.ONE) {
            for (Method method : methods) {
                String sMethodName = method.getName();
                if (sMethodName.toLowerCase().startsWith(Const.STRING_SET)) {
                    String sParameterType = getFirstMethodParameterType(method);
                    if (!RandomUtil.listOfBasicTypes.contains(sParameterType) && !RandomUtil.listOfHashTypes.contains(sParameterType) && !RandomUtil.listOfArrayTypes.contains(sParameterType)) {
                        if (!hsOrmClassesForFill.contains(sParameterType)) {
                            hsOrmClassesForFill.add(sParameterType);
                            hsLevelFill.add(sParameterType);
                        }
                    } else if (!RandomUtil.listOfBasicTypes.contains(sParameterType) && !RandomUtil.listOfHashTypes.contains(sParameterType) && RandomUtil.listOfArrayTypes.contains(sParameterType)) {
                        String sParam = getFirstActualTypeArgumentClass(method.getGenericParameterTypes());
                        if (!RandomUtil.listOfBasicTypes.contains(sParam) && !RandomUtil.listOfHashTypes.contains(sParam) && !RandomUtil.listOfArrayTypes.contains(sParam)) {
                            if (!hsOrmClassesForFill.contains(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()))) {
                                hsOrmClassesForFill.add(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()));
                                hsLevelFill.add(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()));
                            }
                        }
                    } else if (!RandomUtil.listOfBasicTypes.contains(sParameterType) && RandomUtil.listOfHashTypes.contains(sParameterType) && !RandomUtil.listOfArrayTypes.contains(sParameterType)) {
                        Vector<Class<?>> vectorParams = getHashActualTypeArgumentsClass(method.getGenericParameterTypes());
                        if (vectorParams != null) {
                            if (vectorParams != null && !vectorParams.isEmpty()) {
                                for (Class<?> vectorParam : vectorParams) {
                                    String sParam = vectorParam.getName();
                                    if (!RandomUtil.listOfBasicTypes.contains(sParam) && !RandomUtil.listOfHashTypes.contains(sParam) && !RandomUtil.listOfArrayTypes.contains(sParam)) {
                                        if (!hsOrmClassesForFill.contains(sParam)) {
                                            hsOrmClassesForFill.add(sParam);
                                            hsLevelFill.add(sParam);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * filling object with random data
     *
     * @param obj
     * @param methods
     * @param i
     * @return
     */
    private static Object getFilledBasicData(Object obj, Method[] methods, int i) {
        if (obj != null) {
            for (Method method : methods) {
                String sMethodName = method.getName();
                if (sMethodName.toLowerCase().startsWith(Const.STRING_SET)) {
                    String sParameterType = getFirstMethodParameterType(method);
                    if (sParameterType != null) {
                        if (RandomUtil.listOfBasicTypes.contains(sParameterType)) {
                            if (Const.STRING_SET_ID.equalsIgnoreCase(sMethodName)) {
                                invokeMethodOnObject(obj, method, getDinamiclyCastWithValue(sParameterType, String.valueOf(i)));
                            } else {
                                invokeMethodOnObject(obj, method, getRandomBasicObjectValue(sParameterType, sMethodName, obj.getClass().getName()));
                            }
                        } else if (RandomUtil.listOfArrayTypes.contains(sParameterType) && RandomUtil.listOfBasicTypes.contains(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()))) {
                            ArrayList<Object> alTempData = new ArrayList<>();
                            for (int k = Const.ZERO; k < getTempNumberOfData(sParameterType); k++) {
                                alTempData.add(getRandomBasicObjectValue(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()), sMethodName, obj.getClass().getName()));
                            }
                            invokeMethodOnObject(obj, method, alTempData);
                        } else if (RandomUtil.listOfHashTypes.contains(sParameterType)) {
                            Vector<Class<?>> vectorParams = getHashActualTypeArgumentsClass(method.getGenericParameterTypes());
                            if (vectorParams != null && !vectorParams.isEmpty()) {
                                Vector<Object> vector;
                                Object tempObject = null;
                                for (int k = Const.ZERO; k < getTempNumberOfData(sParameterType); k++) {
                                    vector = new Vector<>();
                                    for (Class<?> vectorParam : vectorParams) {
                                        if (RandomUtil.listOfBasicTypes.contains(vectorParam.getName())) {
                                            vector.add(getRandomBasicObjectValue(vectorParam.getName(), false, null, obj.getClass().getName()));
                                        } else {
                                            if (hmFilledClasses.containsKey(vectorParam.getName())) {
                                                Object object = hmFilledClasses.get(vectorParam.getName()).get(RandomUtil.getRandomPrimitiveInt(Const.ZERO, hmFilledClasses.get(vectorParam.getName()).size() - Const.ONE));
                                                vector.add(object);
                                            } else {
                                                getFilledBasicData(vectorParam.getName());
                                                if (hmFilledClasses.containsKey(vectorParam.getName())) {
                                                    Object object = hmFilledClasses.get(vectorParam.getName()).get(RandomUtil.getRandomPrimitiveInt(Const.ZERO, hmFilledClasses.get(vectorParam.getName()).size() - Const.ONE));
                                                    vector.add(object);
                                                }
                                            }
                                        }
                                    }
                                    if (vectorParams.size() > 1) {
                                        tempObject = new HashMap<>();
                                        ((HashMap) tempObject).put(vector.get(Const.ZERO), vector.get(Const.ONE));
                                    }
                                }
                                invokeMethodOnObject(obj, method, tempObject);
                            }
                        }
                    }
                }
            }
        }
        fillHashMapWithObjects(obj.getClass().getName(), obj);
        return obj;
    }

    /**
     * check if max value is bigger then maximum if it is then set it to maximum
     *
     * @param sParameterType
     * @param sValue
     * @return
     */
    private static String checkMinimumMaximumLimitForClass(String sParameterType, String sValue) {
        Class<?> theClass;
        try {
            if (!RandomUtil.mMappingMap.containsKey(sParameterType)) {
                theClass = Class.forName(sParameterType);
            } else {
                theClass = Class.forName(RandomUtil.mMappingMap.get(sParameterType));
            }
            if (sValue != null && !theClass.getName().equals(BigDecimal.class.getName()) && !theClass.getName().equals(BigInteger.class.getName()) && !theClass.getName().equals(Character.class.getName())
                    && !theClass.getName().equals(Date.class.getName()) && !theClass.getName().equals(Boolean.class.getName()) && !theClass.isEnum()) {
                Field[] fields = theClass.getDeclaredFields();
                for (Field field : fields) {
                    if (field.getName().equals(Const.MIN_VALUE)) {
                        if (Double.parseDouble(sValue) < Double.parseDouble(field.get(Const.MIN_VALUE).toString())) {
                            sValue = field.get(Const.MIN_VALUE).toString();
                        }
                    } else if (field.getName().equals(Const.MAX_VALUE)) {
                        if (Double.parseDouble(sValue) > Double.parseDouble(field.get(Const.MAX_VALUE).toString())) {
                            sValue = field.get(Const.MAX_VALUE).toString();
                        }
                    }
                }
            } else if (theClass.getName().equals(Character.class.getName())) {
                if (Integer.parseInt(sValue) < Const.ZERO_INTEGER) {
                    sValue = (Const.ZERO_INTEGER).toString();
                } else if (Integer.parseInt(sValue) > Const.MAX_CHAR) {
                    sValue = Integer.valueOf(Const.MAX_CHAR).toString();
                }
            } else if (theClass.isEnum()) {
                if (sValue.matches(RegularExpUtil.ONLY_DIGITS)) {
                    if (Integer.parseInt(sValue) < Const.ZERO) {
                        sValue = Const.ZERO_STRING;
                    } else if (Integer.parseInt(sValue) > theClass.getEnumConstants().length - Const.ONE) {
                        sValue = String.valueOf(theClass.getEnumConstants().length - Const.ONE);
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
            logger.error(e);
        }
        return sValue;
    }

    /**
     * check if max value is bigger then maximum if it is then set it to maximum
     *
     * @param sParameterType
     * @param boundary
     * @return
     */
    private static void getMinimumMaximumLimitForClass(String sParameterType, Boundary boundary) {
        Class<?> theClass;
        try {
            if (!RandomUtil.mMappingMap.containsKey(sParameterType)) {
                theClass = Class.forName(sParameterType);
            } else {
                theClass = Class.forName(RandomUtil.mMappingMap.get(sParameterType));
            }
            if (!theClass.getName().equals(BigDecimal.class.getName()) && !theClass.getName().equals(BigInteger.class.getName()) && !theClass.getName().equals(Character.class.getName())
                    && !theClass.getName().equals(Date.class.getName()) && !theClass.getName().equals(Boolean.class.getName())) {
                Field[] fields = theClass.getDeclaredFields();
                for (Field field : fields) {
                    if (field.getName().equals(Const.MIN_VALUE)) {
                        boundary.setObjMin(getDinamiclyCastWithValue(sParameterType, field.get(Const.MIN_VALUE).toString()));
                    } else if (field.getName().equals(Const.MAX_VALUE)) {
                        boundary.setObjMax(getDinamiclyCastWithValue(sParameterType, field.get(Const.MAX_VALUE).toString()));
                    }
                }
            } else if (theClass.getName().equals(BigDecimal.class.getName())) {
                if (boundary.getObjMin() == null) {
                    boundary.setObjMin(Const.MIN_VALUE_BIGDECIMAL);
                }
                if (boundary.getObjMax() == null) {
                    boundary.setObjMax(Const.MAX_VALUE_BIGDECIMAL);
                }
                if (boundary.getObjPrecision() == null) {
                    boundary.setObjPrecision(precision);
                }
            } else if (theClass.getName().equals(BigInteger.class.getName())) {
                if (boundary.getObjMin() == null) {
                    boundary.setObjMin(Const.MIN_VALUE_BIGINTEGER);
                }
                if (boundary.getObjMax() == null) {
                    boundary.setObjMax(Const.MAX_VALUE_BIGINTEGER);
                }
            } else if (theClass.getName().equals(Character.class.getName())) {
                if (boundary.getObjMin() == null) {
                    boundary.setObjMin(getDinamiclyCastWithValue(Integer.class.getName(), (Const.ZERO_INTEGER).toString()));
                }
                if (boundary.getObjMax() == null) {
                    boundary.setObjMax(getDinamiclyCastWithValue(Integer.class.getName(), (Integer.valueOf(Const.MAX_CHAR)).toString()));
                }
            } else if (theClass.getName().equals(Date.class.getName())) {
                if (boundary.getObjMin() == null) {
                    long minDate = RandomUtil.getMinimumDate();
                    boundary.setObjMin(new Date(minDate));
                }
                if (boundary.getObjMax() == null) {
                    long maxDate = RandomUtil.getMaximumDate();
                    boundary.setObjMax(new Date(maxDate));
                }
            }
        } catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
            logger.error(e);
        }
    }

    /**
     * dynamically creates object with value from name of the class and value via constructor example new Integer("value"); new sClassName(i);
     *
     * @param sClassName
     * @param value
     * @return
     * @throws NoSuchFieldException
     */
    public static Object getDinamiclyCastWithValue(String sClassName, String value) {
        Object object = null;
        Class<?> theClass;
        try {
            if (Character.class.getName().equals(sClassName) || char.class.getName().equals(sClassName)) {
                theClass = Integer.class;
            } else if (!RandomUtil.mMappingMap.containsKey(sClassName)) {
                theClass = Class.forName(sClassName);
            } else {
                theClass = Class.forName(RandomUtil.mMappingMap.get(sClassName));
            }

            if (theClass.isEnum()) {
                if (value.matches(RegularExpUtil.ONLY_DIGITS)) {
                    object = Integer.parseInt(value);
                } else {
                    object = value.split(Const.COMMA_DELIMITER);
                }
            } else {
                Class<?> sParamFromClass = null;
                Constructor[] allConstructors = theClass.getDeclaredConstructors();
                for (Constructor ctor : allConstructors) {
                    if (sParamFromClass != null && sParamFromClass.getName().equals(String.class.getName())) {
                        break;
                    }
                    Class<?>[] paramType = ctor.getParameterTypes();
                    for (Class<?> aParamType : paramType) {
                        if (sParamFromClass != null && sParamFromClass.getName().equals(String.class.getName())) {
                            break;
                        }
                        sParamFromClass = aParamType;
                        break;
                    }
                }
                Constructor<?> cons = theClass.getConstructor(sParamFromClass);
                object = cons.newInstance(value);
            }
        } catch (ClassNotFoundException e) {
            logger.error(InternationalizationManager.getString(Const.THERE_IS_NO_CLASS_WITH_THE_NAME) + sClassName, e);
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            logger.error(e);
        }
        return object;
    }

    /**
     * fill orm classes in hashmap for lather use of filling relations
     *
     * @param sClassName
     * @param object
     */
    private static void fillHashMapWithObjects(String sClassName, Object object) {
        if (hmFilledClasses.containsKey(sClassName) && !hmFilledClasses.get(sClassName).contains(object)) {
            hmFilledClasses.get(sClassName).add(object);
        } else if (!hmFilledClasses.containsKey(sClassName)) {
            ArrayList<Object> alList = new ArrayList<>();
            alList.add(object);
            hmFilledClasses.put(sClassName, alList);
        }
    }

    /**
     * filling basic types with random data
     *
     * @param sClassName
     * @param bAllowNulls
     * @return Object
     */
    public static Object getRandomBasicObjectValue(String sClassName, boolean bAllowNulls) {
        return getRandomBasicObjectValue(sClassName, bAllowNulls, null, null);
    }

    /**
     * filling basic types with random data
     *
     * @param sClassName
     * @param sMethodName
     * @param sSuperClassName
     * @return Object
     */
    private static Object getRandomBasicObjectValue(String sClassName, String sMethodName, String sSuperClassName) {
        return getRandomBasicObjectValue(sClassName, bAllowNulls, sMethodName, sSuperClassName);
    }

    /**
     * filling basic types with random data
     *
     * @param sClassName
     * @param bAllowNulls
     * @param sMethodName
     * @param sSuperClassName
     * @return Object
     */
    private static Object getRandomBasicObjectValue(String sClassName, boolean bAllowNulls, String sMethodName, String sSuperClassName) {
        Boundary boundary = findMinMaxValueForClass(sClassName, sSuperClassName, sMethodName);
        switch (sClassName) {
            case BasicClassConstants.sInteger:
                return RandomUtil.getRandomInteger((Integer) boundary.getObjMin(), (Integer) boundary.getObjMax(), boundary.isbAllowNulls());
            case BasicClassConstants.sLong:
                return RandomUtil.getRandomLong((Long) boundary.getObjMin(), (Long) boundary.getObjMax(), boundary.isbAllowNulls());
            case BasicClassConstants.sShort:
                return RandomUtil.getRandomShort((Short) boundary.getObjMin(), (Short) boundary.getObjMax(), boundary.isbAllowNulls());
            case BasicClassConstants.sByte:
                return RandomUtil.getRandomByte((Byte) boundary.getObjMin(), (Byte) boundary.getObjMax(), boundary.isbAllowNulls());
            case BasicClassConstants.sBoolean:
                return RandomUtil.getRandomBoolean(bAllowNulls);
            case BasicClassConstants.sDouble:
                return RandomUtil.getRandomDouble((Double) boundary.getObjMin(), (Double) boundary.getObjMax(), Integer.parseInt(boundary.getObjPrecision().toString()), boundary.isbAllowNulls());
            case BasicClassConstants.sFloat:
                return RandomUtil.getRandomFloat((Float) boundary.getObjMin(), (Float) boundary.getObjMax(), Integer.parseInt(boundary.getObjPrecision().toString()), boundary.isbAllowNulls());
            case BasicClassConstants.sCharacter:
                return RandomUtil.getRandomChar((Integer) boundary.getObjMin(), (Integer) boundary.getObjMax());
            case BasicClassConstants.sString:
                return getRandomString(sMethodName, bAllowNulls);
            case BasicClassConstants.sDate:
                return RandomUtil.getRandomDate((Date) boundary.getObjMin(), (Date) boundary.getObjMax(), bAllowNulls);
            case BasicClassConstants.sPrimitiveInt:
                return RandomUtil.getRandomPrimitiveInt((Integer) boundary.getObjMin(), (Integer) boundary.getObjMax());
            case BasicClassConstants.sPrimitiveLong:
                return RandomUtil.getRandomPrimitiveLong((Long) boundary.getObjMin(), (Long) boundary.getObjMax());
            case BasicClassConstants.sPrimitiveShort:
                return RandomUtil.getRandomPrimitiveShort((Short) boundary.getObjMin(), (Short) boundary.getObjMax());
            case BasicClassConstants.sPrimitiveByte:
                return RandomUtil.getRandomPrimitiveByte((Byte) boundary.getObjMin(), (Byte) boundary.getObjMax());
            case BasicClassConstants.sPrimitiveBoolean:
                return RandomUtil.getRandomBoolean(Boolean.FALSE).booleanValue();
            case BasicClassConstants.sPrimitiveFloat:
                return RandomUtil.getRandomPrimitiveFloat((Float) boundary.getObjMin(), (Float) boundary.getObjMax(), Integer.parseInt(boundary.getObjPrecision().toString()));
            case BasicClassConstants.sPrimitiveDouble:
                return RandomUtil.getRandomPrimitiveDouble((Double) boundary.getObjMin(), (Double) boundary.getObjMax(), Integer.parseInt(boundary.getObjPrecision().toString()));
            case BasicClassConstants.sPrimitiveChar:
                return RandomUtil.getRandomChar((Integer) boundary.getObjMin(), (Integer) boundary.getObjMax());
            case BasicClassConstants.sBigDecimal:
                return RandomUtil.getRandomBigDecimal((BigDecimal) boundary.getObjMin(), (BigDecimal) boundary.getObjMax(), Integer.parseInt(boundary.getObjPrecision().toString()), boundary.isbAllowNulls());
            case BasicClassConstants.sBigInteger:
                return RandomUtil.getRandomBigInteger((BigInteger) boundary.getObjMin(), (BigInteger) boundary.getObjMax(), boundary.isbAllowNulls());
            case BasicClassConstants.sEnum:
                Class<?> cl = null;
                if (sSuperClassName != null && sPackageFromClass != null && sMethodName != null) {
                    cl = getReturnParamFromMethod(sSuperClassName.replace(sPackageFromClass + Const.DOT_DELIMITER, Const.STRING_EMPTY), sMethodName.toLowerCase().substring(Const.THREE, sMethodName.length()));
                }
                return RandomUtil.getRandomEnum((int) boundary.getObjMin(), (int) boundary.getObjMax(), boundary.getObjEnum(), cl);
            default:
                return null;
        }
    }

    /**
     * find min max value for boundary for type of Class or Property of the Class
     *
     * @param sClassName
     * @param sSuperClassName
     * @param sMethodName
     * @return
     */
    private static Boundary findMinMaxValueForClass(String sClassName, String sSuperClassName, String sMethodName) {
        Boundary boundary = null;
        if (sMethodName != null) {
            String sPropertyName = sMethodName.replace(Const.STRING_SET, Const.STRING_EMPTY);
            String sClassProperty = (Character.isUpperCase(sPropertyName.charAt(Const.ZERO)) ? Character.toLowerCase(sPropertyName.charAt(Const.ZERO)) + sPropertyName.substring(Const.ONE, sPropertyName.length()) : Character.toUpperCase(sPropertyName.charAt(Const.ZERO)) + sPropertyName.substring(Const.ONE, sPropertyName.length()));
            boundary = getHmBoundary().get(sSuperClassName.replace(sPackageFromClass + Const.DOT_DELIMITER, Const.STRING_EMPTY) + Const.DOT_DELIMITER + sClassProperty);
            if (sClassName.equals(Enum.class.getName()) && boundary == null) {
                boundary = new Boundary();
                boundary.setObjMin(Const.ZERO);
                Class<?> cl = getReturnParamFromMethod(sSuperClassName.replace(sPackageFromClass + Const.DOT_DELIMITER, Const.STRING_EMPTY), sClassProperty);
                boundary.setObjMax(cl.getEnumConstants().length - Const.ONE);
            }
        }
        if (boundary == null) {
            boundary = getHmBoundary().get(sClassName);
        }
        return boundary;
    }

    /**
     * return random string depend of method name
     *
     * @param sMethodName
     * @param bAllowNulls
     * @return
     */
    public static String getRandomString(String sMethodName, boolean bAllowNulls) {
        if (sMethodName != null) {
            if (sMethodName.toLowerCase().contains(Const.STRING_NAME) && !sMethodName.toLowerCase().contains(Const.STRING_FIRST_NAME) && !sMethodName.toLowerCase().contains(Const.STRING_LAST_NAME)) {
                return RandomUtil.getRandomNames(Const.NAME, bAllowNulls);
            } else if (sMethodName.toLowerCase().contains(Const.STRING_FIRST_NAME)) {
                return RandomUtil.getRandomNames(Const.FIRST_NAME, bAllowNulls);
            } else if (sMethodName.toLowerCase().contains(Const.STRING_LAST_NAME)) {
                return RandomUtil.getRandomNames(Const.LAST_NAME, bAllowNulls);
            } else if (sMethodName.toLowerCase().contains(Const.STRING_EMAIL)) {
                return RandomUtil.getRandomNames(Const.EMAIL, bAllowNulls);
            } else {
                return RandomUtil.getRandomUUID(bAllowNulls);
            }
        } else {
            return RandomUtil.getRandomUUID(bAllowNulls);
        }
    }

    /**
     * returns first argument type in method
     *
     * @param method
     * @return
     */
    private static String getFirstMethodParameterType(Method method) {
        String sClassName = null;
        Class<?>[] clParams = method.getParameterTypes();
        if (clParams != null) {
            if (clParams.length == Const.ONE) {
                Class<?> clParam = clParams[Const.ZERO];
                if (clParam.isEnum()) {
                    sClassName = Enum.class.getName();
                } else {
                    sClassName = clParam.getName();
                }
            }
        }
        return sClassName;
    }

    /**
     * invokes method with arguments on object
     *
     * @param obj
     * @param method
     * @param objectArgs
     */
    public static void invokeMethodOnObject(Object obj, Method method, Object objectArgs) {
        try {
            method.invoke(obj, objectArgs);
        } catch (IllegalArgumentException e) {
            logger.error(InternationalizationManager.getString(Const.ILLEGAL_ACCESS_EXCEPTION_IN_METHOD) + " invokeMethodOnObject(Object obj, Method method, Object objectArgs) ", e);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(InternationalizationManager.getString(Const.ILLEGAL_ACCESS_EXCEPTION_IN_METHOD) + "invokeMethodOnObject(Object obj, Method method, Object objectArgs) ", e);
        }
    }

    /**
     * returns actual type arguments form Hash type example HashMap<String><Worker> will return Vector who contains String and Worker class name
     *
     * @param type
     * @return
     */
    private static Vector<Class<?>> getHashActualTypeArgumentsClass(Type[] type) {
        Vector<Class<?>> sParameterClass = new Vector<>();
        Type t = type[Const.ZERO];
        Type[] typeOfParameters = ((ParameterizedType) t).getActualTypeArguments();
        if (typeOfParameters != null) {
            for (Type typeOfParameter : typeOfParameters) {
                Class<?> clOfParameter = (Class<?>) typeOfParameter;
                sParameterClass.add(clOfParameter);
            }
        }
        return sParameterClass;
    }

    /**
     * returns first actual type arguments class name example ArrayList<Worker> returns Worker class name
     *
     * @param type
     * @return
     */
    public static String getFirstActualTypeArgumentClass(Type[] type) {
        if (type.length > Const.ZERO) {
            Type t = type[Const.ZERO];
            Type[] typeOfParameters = ((ParameterizedType) t).getActualTypeArguments();
            if (typeOfParameters != null) {
                Type typeOfParameter = typeOfParameters[Const.ZERO];
                Class<?> clOfParameter = (Class<?>) typeOfParameter;
                return clOfParameter.getName();
            }
        }
        return null;
    }

    /**
     * returns methods from class name
     *
     * @param sClassName
     * @return
     */
    private static Method[] getClassMethods(String sClassName) {
        try {
            Class<?> cl = Class.forName(sClassName);
            if (cl != null) {
                return cl.getDeclaredMethods();
            }
        } catch (ClassNotFoundException e) {
            logger.error(InternationalizationManager.getString(Const.THERE_IS_NO_CLASS_WITH_THE_NAME) + sClassName, e);
            return null;
        }
        return null;
    }

    /**
     * returns returned type of the method
     *
     * @param sClassName
     * @param sFieldName
     * @return
     */
    private static Class<?> getReturnParamFromMethod(String sClassName, String sFieldName) {
        try {
            Class<?> cl = Class.forName(sPackageFromClass + Const.DOT_DELIMITER + sClassName);
            if (cl != null) {
                return cl.getDeclaredField(sFieldName).getType();
            }
        } catch (ClassNotFoundException e) {
            logger.error(InternationalizationManager.getString(Const.THERE_IS_NO_CLASS_WITH_THE_NAME) + Const.SPACE + sClassName, e);
            return null;
        } catch (NoSuchFieldException e) {
            logger.error(InternationalizationManager.getString(Const.THERE_IS_NO_FIELD_WITH_THE_NAME) + Const.SPACE + sFieldName, e);
        } catch (SecurityException e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * creates new instance from class name
     *
     * @param sClassName
     * @return
     */
    private static Object getClassInstance(String sClassName) {
        Object object = null;
        try {
            Class<?> cl = Class.forName(sClassName);
            if (cl != null) {
                try {
                    if (cl.isEnum()) {
                        object = cl.getEnumConstants()[Const.ZERO];
                    } else {
                        object = cl.newInstance();
                    }
                } catch (InstantiationException e) {
                    logger.error(InternationalizationManager.getString(Const.COULD_NOT_INSTANCE_CLASS) + Const.SPACE + sClassName, e);
                    return object;
                } catch (IllegalAccessException e) {
                    logger.error(InternationalizationManager.getString(Const.COULD_NOT_INSTANCE_CLASS) + sClassName, e);
                    return object;
                }
            }
        } catch (ClassNotFoundException e) {
            logger.error(InternationalizationManager.getString(Const.THERE_IS_NO_CLASS_WITH_THE_NAME) + sClassName, e);
            return object;
        }
        return object;
    }
}