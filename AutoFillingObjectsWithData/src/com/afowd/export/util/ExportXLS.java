package com.afowd.export.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.afowd.constants.Const;
import com.afowd.util.RandomUtil;

public class ExportXLS {

	private static String FILE_NAME_XSL = "xls_xlsx/test.xlsx";
	final static Logger logger = Logger.getLogger(ExportXLS.class);
	private static XSSFWorkbook workbook = new XSSFWorkbook();
	private static XSSFCell cell;
	private static XSSFRow row;
	private static int cellNumber = 0;
	private static int rowNumber = 0;
	private static String sCellValue = null;

	/**
	 * exports array list of objects
	 * 
	 * @param alData
	 */
	public static void exportXLSPOI(ArrayList<Object> alData) {
		try {
			FileOutputStream out;
			out = new FileOutputStream(new File(FILE_NAME_XSL));
			XSSFSheet spreadsheet = workbook.createSheet(alData.get(0).getClass().getSimpleName());
			row = spreadsheet.createRow(Const.ZERO);
			setSheetRowNamesForObject(alData.get(0), spreadsheet, Boolean.FALSE);
			setDataForRow(alData, spreadsheet);
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

	}

	/**
	 * set array list of data for rows in xls
	 * 
	 * @param alData
	 * @param spreadsheet
	 */
	private static void setDataForRow(ArrayList<Object> alData, XSSFSheet spreadsheet) {
		if (alData != null && !alData.isEmpty()) {
			for (int i = 0; i < alData.size(); i++) {
				Object object = (Object) alData.get(i);
				rowNumber = 0;
				setDataForRowObject(object, spreadsheet, Boolean.FALSE, i);
			}
			for (int k = 0; k < rowNumber; k++) {
				spreadsheet.autoSizeColumn(k);
			}
		}

	}

	/**
	 * set object data for row in xls
	 * 
	 * @param object
	 * @param spreadsheet
	 * @param bInnerData
	 * @param i
	 */
	private static void setDataForRowObject(Object object, XSSFSheet spreadsheet, Boolean bInnerData, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Method[] methods = ((Object) object).getClass().getDeclaredMethods();
		if (!bInnerData) {
			row = spreadsheet.createRow(i + 1);
		}
		for (int j = 0; j < methods.length; j++) {
			sCellValue = null;
			Method method = methods[j];
			if (method.getName().startsWith(Const.STRING_GET)) {
				try {
					String returnType = method.getReturnType().getName();
					if (returnType.toLowerCase().indexOf("float") >= 0 || returnType.toLowerCase().indexOf("double") >= 0) {
						sCellValue = (method.invoke(object) != null ? method.invoke(object).toString() : "");
					} else if (returnType.toLowerCase().indexOf("date") >= 0) {
						sCellValue = (method.invoke(object) != null ? sdf.format(method.invoke(object)) : "");
					} else if (RandomUtil.listOfArrayTypes.contains(returnType) || RandomUtil.listOfHashTypes.contains(returnType)) {
						sCellValue = method.getReturnType().getSimpleName();
					} else {
						if (!RandomUtil.listOfBasicTypes.contains(returnType) && !RandomUtil.listOfHashTypes.contains(returnType) && !RandomUtil.listOfArrayTypes.contains(returnType) && !bInnerData) {
							setDataForRowObject(method.invoke(object), spreadsheet, Boolean.TRUE, i);
						} else if (RandomUtil.listOfBasicTypes.contains(returnType)) {
							sCellValue = method.invoke(object) != null ? method.invoke(object).toString() : "";
						}

					}
					if (sCellValue != null) {
						cell = row.createCell(rowNumber);
						cell.setCellValue(sCellValue);
						rowNumber++;
					}
				} catch (IllegalArgumentException e) {
					logger.error(e);
				} catch (IllegalAccessException e) {
					logger.error(e);
				} catch (InvocationTargetException e) {
					logger.error(e);
				}
			}
		}
	}
	/***
	 * set header names in sheet for xls
	 * @param object
	 * @param spreadsheet
	 * @param bInnerData
	 */
	private static void setSheetRowNamesForObject(Object object, XSSFSheet spreadsheet, Boolean bInnerData) {
		try {
			Method[] methods = ((Object) object).getClass().getDeclaredMethods();
			for (int j = 0; j < methods.length; j++) {
				Method method = methods[j];
				if (method.getName().startsWith(Const.STRING_GET)) {
					String returnType = method.getReturnType().getName();
					if (!RandomUtil.listOfBasicTypes.contains(returnType) && !RandomUtil.listOfHashTypes.contains(returnType) && !RandomUtil.listOfArrayTypes.contains(returnType) && !bInnerData) {
						setSheetRowNamesForObject(method.invoke(object), spreadsheet, Boolean.TRUE);
					} else if (RandomUtil.listOfHashTypes.contains(returnType) || RandomUtil.listOfArrayTypes.contains(returnType)) {
						String sNameOfTheRow = object.getClass().getSimpleName() + "." + method.getName().substring(3, method.getName().length());
						cell = row.createCell(cellNumber);
						cell.setCellStyle(getCellStyleHeader());
						cell.setCellValue(sNameOfTheRow);
						cellNumber = cellNumber + 1;
					} else if (RandomUtil.listOfBasicTypes.contains(returnType)) {
						String sNameOfTheRow = object.getClass().getSimpleName() + "." + method.getName().substring(3, method.getName().length());
						cell = row.createCell(cellNumber);
						cell.setCellStyle(getCellStyleHeader());
						cell.setCellValue(sNameOfTheRow);
						cellNumber = cellNumber + 1;
					}
				}
			}
		} catch (InvocationTargetException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		}
	}
	/**
	 * create cell style for header names
	 * @return
	 */
	private static XSSFCellStyle getCellStyleHeader() {
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		Font headerFont = workbook.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFont(headerFont);
		return cellStyle;
	}

	public static String getFILE_NAME_XSL() {
		return FILE_NAME_XSL;
	}

	public static void setFILE_NAME_XSL(String fILE_NAME_XSL) {
		FILE_NAME_XSL = fILE_NAME_XSL;
	}
}
