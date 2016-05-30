package com.afowd.console.print;

import com.afowd.constants.Const;
import com.afowd.internationalizationManager.InternationalizationManager;
import com.afowd.range.filter.RangeNumberFilter;
import com.afowd.sort.SortUtil;
import com.afowd.util.DateUtil;
import com.afowd.util.RandomDataUtil;
import com.afowd.util.RandomUtil;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ConsolePrintUtil {
    private final static Logger logger = Logger.getLogger(ConsolePrintUtil.class);
    private static String lineOutput = "";
    private static SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.sDateFormat);

    /**
     * printing in console array list of range number filter data
     *
     * @param alRangeNumberFilters
     */
    public static void printFilterData(ArrayList<RangeNumberFilter> alRangeNumberFilters) {
        for (RangeNumberFilter rangeNumberFilter : alRangeNumberFilters) {
            logger.info(Const.NEW_LINE + InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FOR_PROPERTY) + rangeNumberFilter.getProperty() + Const.NEW_LINE
                    + InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FROM) + rangeNumberFilter.getRangeFrom() + Const.NEW_LINE
                    + InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_TO) + rangeNumberFilter.getRangeTo() + Const.NEW_LINE + InternationalizationManager.getString(Const.IN_RANGE)
                    + (rangeNumberFilter.isInRange() ? InternationalizationManager.getString(Const.TRUE) : InternationalizationManager.getString(Const.FALSE)));
        }
    }

    /**
     * printing in console sort order
     *
     * @param sortUtil
     */
    public static void printSortOrder(SortUtil sortUtil) {
        logger.info(Const.NEW_LINE + InternationalizationManager.getString(Const.SORT_CLASS) + sortUtil.getClassToCompare() + Const.NEW_LINE + InternationalizationManager.getString(Const.SORT_PROPERTY)
                + sortUtil.getSortByProperty());
    }

    /**
     * printing in console range number filter data
     *
     * @param rangeNumberFilter
     */
    public static void printFilterData(RangeNumberFilter rangeNumberFilter) {
        logger.info(Const.NEW_LINE + InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FOR_PROPERTY) + rangeNumberFilter.getProperty() + Const.NEW_LINE
                + InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FROM) + rangeNumberFilter.getRangeFrom() + Const.NEW_LINE + InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_TO)
                + rangeNumberFilter.getRangeTo() + Const.NEW_LINE + InternationalizationManager.getString(Const.IN_RANGE)
                + (rangeNumberFilter.isInRange() ? InternationalizationManager.getString(Const.TRUE) : InternationalizationManager.getString(Const.FALSE)) + Const.NEW_LINE
                + InternationalizationManager.getString(Const.ALLOW_NULLS)
                + (rangeNumberFilter.isAllowNull() ? InternationalizationManager.getString(Const.TRUE) : InternationalizationManager.getString(Const.FALSE)));

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
                lineOutput += object.getClass().getSimpleName() + Const.DOT_DELIMITER + method.getName().substring(3, method.getName().length()) + ":\t";
                try {
                    String returnType = method.getReturnType().getName();
                    if (returnType.toLowerCase().contains("float") || returnType.toLowerCase().contains("double")) {
                        lineOutput += (method.invoke(object, (Object[]) null) != null ? method.invoke(object) : Const.STRING_EMPTY) + "\t";
                    } else if (returnType.toLowerCase().contains("date")) {
                        lineOutput += (method.invoke(object, (Object[]) null) != null ? sdf.format(method.invoke(object)) : Const.STRING_EMPTY) + "\t";
                    } else if (returnType.equals(ArrayList.class.getName())) {
//                            TODO Array print format
                    } else {
                        if (!RandomUtil.listOfBasicTypes.contains(returnType) && !RandomUtil.listOfHashTypes.contains(returnType) && !RandomUtil.listOfArrayTypes.contains(returnType)
                                && !bInnerData) {
                            Object tempObj = method.invoke(object);
                            if (tempObj != null) {
                                if (tempObj instanceof Enum) {
                                    lineOutput += tempObj.toString() + "\t";
                                } else {
                                    printData(method.invoke(object), Boolean.TRUE);
                                }
                            } else {
                                lineOutput += Const.NULL + "\t";
                            }
                        } else {
                            lineOutput += method.invoke(object) + "\t";
                        }
                    }
                } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                    logger.error(e);
                }
            }
        }
        if (!bInnerData) {
            lineOutput += Const.NEW_LINE;
        }
    }

    /**
     * printing data for array list of objects
     *
     * @param alData
     */
    public static void printData(ArrayList<Object> alData) {
        if (alData != null && !alData.isEmpty()) {
            for (Object object : alData) {
                printData(object, Boolean.FALSE);
            }
            lineOutput += Const.NEW_LINE;
            logger.info(Const.NEW_LINE + lineOutput + Const.NEW_LINE);
            logger.info("\t\t\t\t\t\t\t\t\t\t\t" + InternationalizationManager.getString(Const.SIZE) + " : " + alData.size() + " " + InternationalizationManager.getString(Const.FROM) + RandomDataUtil.getNumberOfData() + "\n##############################################################################################################################################################################################" + "\n\n");
        } else {
            logger.info(Const.NEW_LINE + InternationalizationManager.getString(Const.ERROR_NO_DATA) + Const.NEW_LINE);
        }
    }
}
