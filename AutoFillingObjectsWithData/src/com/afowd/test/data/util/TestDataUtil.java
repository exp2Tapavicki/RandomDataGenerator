package com.afowd.test.data.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.afowd.console.print.ConsolePrintUtil;
import com.afowd.constants.Const;
import com.afowd.counter.TimeCounter;
import com.afowd.export.util.ExportXLS;
import com.afowd.jasperreports.util.JasperReportsUtil;
import com.afowd.ormClasses.Worker;
import com.afowd.range.filter.RangeNumberFilter;
import com.afowd.sort.SortUtil;
import com.afowd.util.FilterUtil;
import com.afowd.util.RandomDataUtil;
import com.afowd.util.RandomUtil;
import com.afowd.xml.util.XSteamUtil;

public class TestDataUtil {
	final static Logger logger = Logger.getLogger(TestDataUtil.class);
	/**
	 * creating test data, range number filter, filtering data and printing in console
	 */
	public static void printAndTestResultOneFilter(){
		TimeCounter timeCounter = new TimeCounter();
		RandomDataUtil.setDeepLevel(3);
		RandomDataUtil.setNumberOfData(10);
		RandomDataUtil.setNumberOfDataForOneToManyRelation(3);
		RandomDataUtil.setbAllowNulls(Boolean.TRUE);
		RandomDataUtil.setPrecision(Const.FIVE);
		RandomDataUtil.setsPackageFromClass("com.afowd.ormClasses");
		RandomDataUtil.setsClassName(Worker.class.getName());
		ArrayList<Object> alData =  new ArrayList<Object>();
		alData = RandomDataUtil.getFilledData();
		RangeNumberFilter rangeNumberFilter = RandomDataUtil.getRandomRangeNumberFilter(Worker.class.getSimpleName(),"yearsOfExpirenceTotal");		
		ConsolePrintUtil.printData(alData);
		alData = FilterUtil.filterRangeDataNumbers(alData, rangeNumberFilter);
		ConsolePrintUtil.printData(alData);
		ConsolePrintUtil.printFilterData(rangeNumberFilter);
		logger.info(timeCounter.printUsedTime());
	}
	/**
	 * creating two range number filter, test data, filtering  and printing in console
	 */
	public static void printAndTestResultMoreFillters(){
		TimeCounter timeCounter = new TimeCounter();
		RandomDataUtil.setDeepLevel(3);
		RandomDataUtil.setNumberOfData(10);
		RandomDataUtil.setNumberOfDataForOneToManyRelation(3);
		RandomDataUtil.setbAllowNulls(Boolean.TRUE);
		RandomDataUtil.setPrecision(Const.FIVE);
		RandomDataUtil.setsPackageFromClass("com.afowd.ormClasses");
		RandomDataUtil.setsClassName(Worker.class.getName());
		RangeNumberFilter rangeNumberFilter = RandomDataUtil.getRandomRangeNumberFilter(Worker.class.getSimpleName(),"yearsOfExpirenceTotal");		
		RangeNumberFilter rangeNumberFilter2 = new RangeNumberFilter(RandomUtil.getRandomLong(Const.ZERO_LONG, Long.MAX_VALUE, Boolean.FALSE), RandomUtil.getRandomLong(Const.ZERO_LONG, Long.MAX_VALUE, Boolean.FALSE), RandomUtil.getRandomBoolean(Boolean.FALSE), "Worker", "age");
		ArrayList<RangeNumberFilter> alRangeNumberFilters = new ArrayList<RangeNumberFilter>();
		alRangeNumberFilters.add(rangeNumberFilter);
		alRangeNumberFilters.add(rangeNumberFilter2);
		ArrayList<Object> alData =  new ArrayList<Object>();
		alData = RandomDataUtil.getFilledData();
		ConsolePrintUtil.printData(alData);
		alData = FilterUtil.filterRangeDataNumbers(alData, alRangeNumberFilters);
		ConsolePrintUtil.printData(alData);
		ConsolePrintUtil.printFilterData(alRangeNumberFilters);
		logger.info(timeCounter.printUsedTime());
	}
	/**
	 * creating and sorting array list of data and printing in console
	 */
	public static void printAndTestResultSortOrder(){
		TimeCounter timeCounter = new TimeCounter();
		ArrayList<Object> alData =  new ArrayList<Object>();
		RandomDataUtil.setDeepLevel(3);
		RandomDataUtil.setNumberOfData(10);
		RandomDataUtil.setNumberOfDataForOneToManyRelation(3);
		RandomDataUtil.setbAllowNulls(Boolean.TRUE);
		RandomDataUtil.setPrecision(Const.FIVE);
		RandomDataUtil.setsPackageFromClass("com.afowd.ormClasses");
		RandomDataUtil.setsClassName(Worker.class.getName());
		alData = RandomDataUtil.getFilledData();
		ConsolePrintUtil.printData(alData);
		SortUtil sortUtil = new SortUtil("Worker", "age");
		sortUtil.sortBeanByPropertyASC(alData);
		ConsolePrintUtil.printData(alData);
		ConsolePrintUtil.printSortOrder(sortUtil);
		logger.info(timeCounter.printUsedTime());
	}
	/**
	 * export objects to xml
	 */
	public static void exportObjectsToXML() {
		TimeCounter timeCounter = new TimeCounter();
		ArrayList<Object> alData =  new ArrayList<Object>();
		RandomDataUtil.setDeepLevel(1);
		RandomDataUtil.setNumberOfData(4);
		RandomDataUtil.setNumberOfDataForOneToManyRelation(3);
		RandomDataUtil.setbAllowNulls(Boolean.TRUE);
		RandomDataUtil.setPrecision(Const.FIVE);
		RandomDataUtil.setsPackageFromClass("com.afowd.ormClasses");
		RandomDataUtil.setsClassName(Worker.class.getName());
		alData = RandomDataUtil.getFilledData();
		ConsolePrintUtil.printData(alData);
		for (int i = 0; i < alData.size(); i++) {
			Worker object = (Worker) alData.get(i);
			XSteamUtil.xSteamObjectToXML(object, XSteamUtil.getFILE_NAME() + i + ".xml");
		}
		logger.info(timeCounter.printUsedTime());
	}
	
	/**
	 * export objects to xml
	 */
	public static void XMLToObject() {
		TimeCounter timeCounter = new TimeCounter();
		ArrayList<Object> alData =  new ArrayList<Object>();
		int i = 0;
		while (XSteamUtil.xSteamXMLToObject(XSteamUtil.getFILE_NAME() + i + ".xml" ) != null) {
			Object object = (Object) XSteamUtil.xSteamXMLToObject(XSteamUtil.getFILE_NAME() + i + ".xml" );
			alData.add(object);
			i++;
		} 
		ConsolePrintUtil.printData(alData);
		logger.info(timeCounter.printUsedTime());
	}
	/**
	 * export objects to xsl
	 */
	public static void exportXMLtoXSL() {
		TimeCounter timeCounter = new TimeCounter();
		ArrayList<Object> alData =  new ArrayList<Object>();
		RandomDataUtil.setDeepLevel(3);
		RandomDataUtil.setNumberOfData(10);
		RandomDataUtil.setNumberOfDataForOneToManyRelation(3);
		RandomDataUtil.setbAllowNulls(Boolean.TRUE);
		RandomDataUtil.setPrecision(Const.FIVE);
		RandomDataUtil.setsPackageFromClass("com.afowd.ormClasses");
		RandomDataUtil.setsClassName(Worker.class.getName());
		alData = RandomDataUtil.getFilledData();
		ExportXLS.exportXLSPOI(alData);
		logger.info(timeCounter.printUsedTime());
	}
	
	/**
	 * create jasper report
	 */
	public static void createJasperReport() {
		TimeCounter timeCounter = new TimeCounter();
		ArrayList<Object> alData =  new ArrayList<Object>();
		RandomDataUtil.setDeepLevel(3);
		RandomDataUtil.setNumberOfData(10);
		RandomDataUtil.setNumberOfDataForOneToManyRelation(3);
		RandomDataUtil.setbAllowNulls(Boolean.FALSE);
		RandomDataUtil.setPrecision(Const.FIVE);
		RandomDataUtil.setsPackageFromClass("com.afowd.ormClasses");
		RandomDataUtil.setsClassName(Worker.class.getName());
		alData = RandomDataUtil.getFilledData();
		JasperReportsUtil.getReport(alData);
		logger.info(timeCounter.printUsedTime());
	}
	/**
	 * create jasper report and export to PDF
	 */
	public static void createJasperReportExportPDF() {
		TimeCounter timeCounter = new TimeCounter();
		ArrayList<Object> alData =  new ArrayList<Object>();
		RandomDataUtil.setDeepLevel(3);
		RandomDataUtil.setNumberOfData(10);
		RandomDataUtil.setNumberOfDataForOneToManyRelation(3);
		RandomDataUtil.setbAllowNulls(Boolean.FALSE);
		RandomDataUtil.setPrecision(Const.FIVE);
		RandomDataUtil.setsPackageFromClass("com.afowd.ormClasses");
		RandomDataUtil.setsClassName(Worker.class.getName());
		alData = RandomDataUtil.getFilledData();
		JasperReportsUtil.exportToPDF(alData);
		logger.info(timeCounter.printUsedTime());
	}
	/**
	 * create jasper report and export to XSL
	 */
//	public static void createJasperReportExportXSL() {
//		TimeCounter timeCounter = new TimeCounter();
//		ArrayList<Object> alData =  new ArrayList<Object>();
//		RandomDataUtil.setDeepLevel(3);
//		RandomDataUtil.setNumberOfData(10);
//		RandomDataUtil.setNumberOfDataForOneToManyRelation(3);
//		RandomDataUtil.setbAllowNulls(Boolean.FALSE);
//		RandomDataUtil.setPrecision(Const.FIVE);
//		RandomDataUtil.setsPackageFromClass("com.afowd.ormClasses");
//		RandomDataUtil.setsClassName(Worker.class.getName());
//		alData = RandomDataUtil.getFilledData();
//		JasperReportsUtil.exportToXSL(alData);
//		logger.info(timeCounter.printUsedTime());
//	}
}
