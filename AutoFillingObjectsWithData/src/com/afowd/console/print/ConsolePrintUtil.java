package com.afowd.console.print;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.afowd.constants.Const;
import com.afowd.internationalizationManager.InternationalizationManager;
import com.afowd.range.filter.RangeNumberFilter;
import com.afowd.sort.SortUtil;
import com.afowd.util.RandomDataUtil;
import com.afowd.util.RandomUtil;

public class ConsolePrintUtil {
	final static Logger logger = Logger.getLogger(ConsolePrintUtil.class);
	private static String lineOutput = "";
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	/**
	 * printing in console array list of range number filter data
	 * 
	 * @param alRangeNumberFilters
	 */
	public static void printFilterData(ArrayList<RangeNumberFilter> alRangeNumberFilters) {
		for (Iterator<RangeNumberFilter> iterator = alRangeNumberFilters.iterator(); iterator.hasNext();) {
			RangeNumberFilter rangeNumberFilter = (RangeNumberFilter) iterator.next();
			logger.info("\n" + InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FOR_PROPERTY) + rangeNumberFilter.getProperty() + "\n"
					+ InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FROM) + rangeNumberFilter.getRangeFrom() + "\n"
					+ InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_TO) + rangeNumberFilter.getRangeTo() + "\n" + InternationalizationManager.getString(Const.IN_RANGE)
					+ (rangeNumberFilter.getIsInRange() ? InternationalizationManager.getString(Const.TRUE) : InternationalizationManager.getString(Const.FALSE)));
		}
	}

	/**
	 * printing in console sort order
	 * 
	 * @param alRangeNumberFilters
	 */
	public static void printSortOrder(SortUtil sortUtil) {
		logger.info("\n" + InternationalizationManager.getString(Const.SORT_CLASS) + sortUtil.getClassToCompare() + "\n" + InternationalizationManager.getString(Const.SORT_PROPERTY)
				+ sortUtil.getSortByProperty());
	}

	/**
	 * printing in console range number filter data
	 * 
	 * @param rangeNumberFilter
	 */
	public static void printFilterData(RangeNumberFilter rangeNumberFilter) {
		logger.info("\n" + InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FOR_PROPERTY) + rangeNumberFilter.getProperty() + "\n"
				+ InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_FROM) + rangeNumberFilter.getRangeFrom() + "\n" + InternationalizationManager.getString(Const.RANGE_NUMBER_FILTER_TO)
				+ rangeNumberFilter.getRangeTo() + "\n" + InternationalizationManager.getString(Const.IN_RANGE)
				+ (rangeNumberFilter.getIsInRange() ? InternationalizationManager.getString(Const.TRUE) : InternationalizationManager.getString(Const.FALSE)) + "\n"
				+ InternationalizationManager.getString(Const.ALLOW_NULLS)
				+ (rangeNumberFilter.getAllowNull() ? InternationalizationManager.getString(Const.TRUE) : InternationalizationManager.getString(Const.FALSE)));

	}

	/**
	 * printing data for array list of objects
	 * 
	 * @param alData
	 */
	public static void printData(Object object, String sClassName, boolean bInnerData) {
		Method[] methods = ((Object) object).getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().startsWith(Const.STRING_GET)){
				lineOutput += object.getClass().getSimpleName() + "." + method.getName().substring(3, method.getName().length()) + ":\t" ;
				try {
					String returnType = method.getReturnType().getName();
					if (returnType.toLowerCase().indexOf("float") >= 0 || returnType.toLowerCase().indexOf("double") >= 0 ){
						Object object1 =  method.invoke(object, null);
						lineOutput += (method.invoke(object, null) != null ? method.invoke(object, null) : "") + "\t";
					} else if (returnType.toLowerCase().indexOf("date") >= 0){
						lineOutput += (method.invoke(object, null) != null ? sdf.format(method.invoke(object, null)) : "") + "\t";
					} else if (returnType.equals(ArrayList.class.getName())){
						
					} else {
						if (!RandomUtil.listOfBasicTypes.contains(returnType) && !RandomUtil.listOfHashTypes.contains(returnType) && !RandomUtil.listOfArrayTypes.contains(returnType)
								&& !bInnerData){
							printData(method.invoke(object, null), method.invoke(object, null).getClass().getSimpleName(), Boolean.TRUE);
						} else {
							lineOutput += method.invoke(object, null) + "\t";
						}
					}
				} catch (IllegalArgumentException e) {
					logger.error(e);
				} catch (IllegalAccessException e) {
					logger.error(e);;
				} catch (InvocationTargetException e) {
					logger.error(e);
				}
			}
		}
		if (!bInnerData){
			lineOutput += "\n";
		}
	}
	/**
	 * printing data for array list of objects
	 * @param alData
	 */
	public static void printData(ArrayList<Object> alData) {
		if (alData != null && !alData.isEmpty()){
			for (Iterator<Object> iterator = alData.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				printData(object, object.getClass().getSimpleName(), Boolean.FALSE);
			}
			lineOutput += "\n";
			logger.info("\n" + lineOutput + "\n");
			logger.info("\t\t\t\t\t\t\t\t\t\t\t" + InternationalizationManager.getString(Const.SIZE) + " : " + alData.size() + " " + InternationalizationManager.getString(Const.FROM) + RandomDataUtil.getNumberOfData() + "\n##############################################################################################################################################################################################" + "\n\n");
		} else {
			logger.info("\n" + InternationalizationManager.getString(Const.ERROR_NO_DATA) + "\n");
		}
	}
}
