package com.rdg.console.print;

import com.rdg.constants.Const;
import com.rdg.internationalizationManager.InternationalizationManager;
import com.rdg.range.filter.RangeNumberFilter;
import com.rdg.sort.SortUtil;
import com.rdg.util.DateUtil;
import com.rdg.util.RandomDataUtil;
import com.rdg.util.RandomUtil;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

public class ConsolePrintUtil {
    private final static Logger logger = Logger.getLogger(ConsolePrintUtil.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.sDateFormat);
    public static StringBuilder stringBuilder = new StringBuilder();

    /**
     * printing in console array list of range number filter data
     *
     * @param alRangeNumberFilters
     */
    public static void printFilterData(ArrayList<RangeNumberFilter> alRangeNumberFilters) {
        for (RangeNumberFilter rangeNumberFilter : alRangeNumberFilters) {
            stringBuilder.append(Const.NEW_LINE);
            stringBuilder.append(InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FOR_PROPERTY));
            stringBuilder.append(rangeNumberFilter.getProperty());
            stringBuilder.append(Const.NEW_LINE);
            stringBuilder.append(InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FROM));
            stringBuilder.append(rangeNumberFilter.getRangeFrom());
            stringBuilder.append(Const.NEW_LINE);
            stringBuilder.append(InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_TO));
            stringBuilder.append(rangeNumberFilter.getRangeTo());
            stringBuilder.append(Const.NEW_LINE);
            stringBuilder.append(InternationalizationManager.getString(Const.IN_RANGE));
            stringBuilder.append(rangeNumberFilter.isInRange() ? InternationalizationManager.getString(Const.TRUE) : InternationalizationManager.getString(Const.FALSE));
        }
        logger.info(stringBuilder.toString());
    }

    /**
     * printing in console sort order
     *
     * @param sortUtil
     */
    public static void printSortOrder(SortUtil sortUtil) {
        stringBuilder.append(Const.NEW_LINE);
        stringBuilder.append(InternationalizationManager.getString(Const.SORT_CLASS));
        stringBuilder.append(sortUtil.getClassToCompare());
        stringBuilder.append(Const.NEW_LINE);
        stringBuilder.append(InternationalizationManager.getString(Const.SORT_PROPERTY));
        stringBuilder.append(sortUtil.getSortByProperty());
        logger.info(stringBuilder.toString());
    }

    /**
     * printing in console range number filter data
     *
     * @param rangeNumberFilter
     */
    public static void printFilterData(RangeNumberFilter rangeNumberFilter) {
        stringBuilder.append(Const.NEW_LINE);
        stringBuilder.append(InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FOR_PROPERTY));
        stringBuilder.append(rangeNumberFilter.getProperty());
        stringBuilder.append(Const.NEW_LINE);
        stringBuilder.append(InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FROM));
        stringBuilder.append(rangeNumberFilter.getRangeFrom());
        stringBuilder.append(Const.NEW_LINE);
        stringBuilder.append(InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_TO));
        stringBuilder.append(rangeNumberFilter.getRangeTo());
        stringBuilder.append(Const.NEW_LINE);
        stringBuilder.append(InternationalizationManager.getString(Const.IN_RANGE));
        stringBuilder.append(rangeNumberFilter.isInRange() ? InternationalizationManager.getString(Const.TRUE) : InternationalizationManager.getString(Const.FALSE));
        stringBuilder.append(Const.NEW_LINE);
        stringBuilder.append(InternationalizationManager.getString(Const.ALLOW_NULLS));
        stringBuilder.append(rangeNumberFilter.isAllowNull() ? InternationalizationManager.getString(Const.TRUE) : InternationalizationManager.getString(Const.FALSE));
        logger.info(stringBuilder.toString());

    }

    /**
     * printing data for array list of objects
     *
     * @param object
     * @param bInnerData
     */
    private static void printData(Object object, boolean bInnerData) {
        Method[] methods = ((Object) object).getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith(Const.STRING_GET)) {
                stringBuilder.append(object.getClass().getSimpleName());
                stringBuilder.append(Const.DOT_DELIMITER);
                stringBuilder.append(method.getName().substring(Const.THREE, method.getName().length()));
                stringBuilder.append(Const.COLON);
                stringBuilder.append(Const.TAB);
                try {
                    String returnType = method.getReturnType().getName();
                    if (returnType.toLowerCase().contains("float") || returnType.toLowerCase().contains("double")) {
                        stringBuilder.append(method.invoke(object, (Object[]) null) != null ? method.invoke(object) : Const.STRING_EMPTY);
                        stringBuilder.append(Const.TAB);
                    } else if (returnType.toLowerCase().contains("date")) {
                        stringBuilder.append(method.invoke(object, (Object[]) null) != null ? sdf.format(method.invoke(object)) : Const.STRING_EMPTY);
                        stringBuilder.append(Const.TAB);
                    } else if (returnType.equals(ArrayList.class.getName())) {
//                            TODO Array print format
                    } else {
                        if (!RandomUtil.listOfBasicTypes.contains(returnType) && !RandomUtil.listOfHashTypes.contains(returnType) && !RandomUtil.listOfArrayTypes.contains(returnType)
                                && !bInnerData) {
                            Object tempObj = method.invoke(object);
                            if (tempObj != null) {
                                if (tempObj instanceof Enum) {
                                    stringBuilder.append(tempObj.toString());
                                    stringBuilder.append(Const.TAB);
                                } else {
                                    printData(method.invoke(object), Boolean.TRUE);
                                }
                            } else {
                                stringBuilder.append(Const.NULL);
                                stringBuilder.append(Const.TAB);
                            }
                        } else {
                            stringBuilder.append(method.invoke(object));
                            stringBuilder.append(Const.TAB);
                        }
                    }
                } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                    logger.error(e);
                }
            }
        }
        if (!bInnerData) {
            stringBuilder.append(Const.NEW_LINE);
        }
    }

    /**
     * printing data for array list of objects
     *
     * @param alData
     */
    public static void printData(ArrayList<Object> alData) {
        if (alData != null && !alData.isEmpty()) {
            stringBuilder.append(Const.NEW_LINE);
            for (Object object : alData) {
                printData(object, Boolean.FALSE);
            }
            stringBuilder.append(Const.NEW_LINE);
            logger.info(stringBuilder.toString());
            StringBuilder sbInfo = new StringBuilder();
            sbInfo.append(Const.TAB);
            sbInfo.append(Const.TAB);
            sbInfo.append(Const.TAB);
            sbInfo.append(Const.TAB);
            sbInfo.append(Const.TAB);
            sbInfo.append(Const.TAB);
            sbInfo.append(Const.TAB);
            sbInfo.append(Const.TAB);
            sbInfo.append(Const.TAB);
            sbInfo.append(Const.TAB);
            sbInfo.append(Const.TAB);
            sbInfo.append(InternationalizationManager.getString(Const.SIZE));
            sbInfo.append(Const.SPACE);
            sbInfo.append(Const.COLON);
            sbInfo.append(Const.SPACE);
            sbInfo.append(alData.size());
            sbInfo.append(Const.SPACE);
            sbInfo.append(InternationalizationManager.getString(Const.FROM));
            sbInfo.append(Const.SPACE);
            sbInfo.append(RandomDataUtil.getNumberOfData());
            sbInfo.append(Const.NEW_LINE);
            sbInfo.append(Const.HASH_ARRAY);
            sbInfo.append(Const.NEW_LINE);
            sbInfo.append(Const.NEW_LINE);
            logger.info(sbInfo.toString());
        } else {
            stringBuilder.append(Const.NEW_LINE);
            stringBuilder.append(InternationalizationManager.getString(Const.ERROR_NO_DATA));
            stringBuilder.append(Const.NEW_LINE);
            logger.info(stringBuilder.toString());
        }
    }
}

