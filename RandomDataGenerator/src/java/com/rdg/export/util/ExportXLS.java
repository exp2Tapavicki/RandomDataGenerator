package com.rdg.export.util;

import com.rdg.constants.BasicClassConstants;
import com.rdg.constants.Const;
import com.rdg.util.DateUtil;
import com.rdg.util.RandomUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class ExportXLS {

    final static Logger logger = Logger.getLogger(ExportXLS.class);
    private static String FILE_NAME_XSL = "RandomDataGenerator/xls_xlsx/test.xlsx";
    private static XSSFWorkbook workbook = new XSSFWorkbook();
    private static XSSFCell cell;
    private static XSSFRow row;
    private static int cellNumber = Const.ZERO;
    private static int rowNumber = Const.ZERO;

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
            setSheetRowNamesForObject(alData.get(0), Boolean.FALSE);
            setDataForRow(alData, spreadsheet);
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            logger.error(e);
        }

    }

    /**
     * set array list of data for rows in xls
     *
     * @param alData      alData
     * @param spreadsheet spreadsheet
     */
    private static void setDataForRow(ArrayList<Object> alData, XSSFSheet spreadsheet) {
        if (alData != null && !alData.isEmpty()) {
            for (int i = Const.ZERO; i < alData.size(); i++) {
                Object object = alData.get(i);
                rowNumber = Const.ZERO;
                setDataForRowObject(object, spreadsheet, Boolean.FALSE, i);
            }
            for (int k = Const.ZERO; k < rowNumber; k++) {
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
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.sDateFormat);
        Method[] methods = ((Object) object).getClass().getDeclaredMethods();
        if (!bInnerData) {
            row = spreadsheet.createRow(i + Const.ONE);
        }
        for (Method method : methods) {
            String sCellValue = null;
            if (method.getName().startsWith(Const.STRING_GET)) {
                try {
                    String returnType = method.getReturnType().getName();
                    if (returnType.toLowerCase().contains(BasicClassConstants.sPrimitiveFloat) || returnType.toLowerCase().contains(BasicClassConstants.sPrimitiveDouble)) {
                        sCellValue = (method.invoke(object) != null ? method.invoke(object).toString() : Const.STRING_EMPTY);
                    } else if (returnType.toLowerCase().contains(BasicClassConstants.sDate.toLowerCase())) {
                        sCellValue = (method.invoke(object) != null ? sdf.format(method.invoke(object)) : Const.STRING_EMPTY);
                    } else if (RandomUtil.listOfArrayTypes.contains(returnType) || RandomUtil.listOfHashTypes.contains(returnType)) {
                        sCellValue = method.getReturnType().getSimpleName();
                    } else {
                        if (!RandomUtil.listOfBasicTypes.contains(returnType) && !RandomUtil.listOfHashTypes.contains(returnType) && !RandomUtil.listOfArrayTypes.contains(returnType) && !bInnerData) {
                            setDataForRowObject(method.invoke(object), spreadsheet, Boolean.TRUE, i);
                        } else if (RandomUtil.listOfBasicTypes.contains(returnType)) {
                            sCellValue = method.invoke(object) != null ? method.invoke(object).toString() : Const.STRING_EMPTY;
                        }
                    }
                    if (sCellValue != null) {
                        cell = row.createCell(rowNumber);
                        cell.setCellValue(sCellValue);
                        rowNumber++;
                    }
                } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                    logger.error(e);
                }
            }
        }
    }

    /***
     * set header names in sheet for xls
     *
     * @param object
     * @param bInnerData
     */
    private static void setSheetRowNamesForObject(Object object, Boolean bInnerData) {
        try {
            Method[] methods = ((Object) object).getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().startsWith(Const.STRING_GET)) {
                    String returnType = method.getReturnType().getName();
                    if (!RandomUtil.listOfBasicTypes.contains(returnType) && !RandomUtil.listOfHashTypes.contains(returnType) && !RandomUtil.listOfArrayTypes.contains(returnType) && !bInnerData) {
                        setSheetRowNamesForObject(method.invoke(object), Boolean.TRUE);
                    } else if (RandomUtil.listOfHashTypes.contains(returnType) || RandomUtil.listOfArrayTypes.contains(returnType)) {
                        String sNameOfTheRow = object.getClass().getSimpleName() + Const.DOT_DELIMITER + method.getName().substring(Const.THREE, method.getName().length());
                        cell = row.createCell(cellNumber);
                        cell.setCellStyle(getCellStyleHeader());
                        cell.setCellValue(sNameOfTheRow);
                        cellNumber = cellNumber + Const.ONE;
                    } else if (RandomUtil.listOfBasicTypes.contains(returnType)) {
                        String sNameOfTheRow = object.getClass().getSimpleName() + Const.DOT_DELIMITER + method.getName().substring(Const.THREE, method.getName().length());
                        cell = row.createCell(cellNumber);
                        cell.setCellStyle(getCellStyleHeader());
                        cell.setCellValue(sNameOfTheRow);
                        cellNumber = cellNumber + Const.ONE;
                    }
                }
            }
        } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
            logger.error(e);
        }
    }

    /**
     * create cell style for header names
     *
     * @return
     */
    private static XSSFCellStyle getCellStyleHeader() {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        Font headerFont = workbook.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(DateUtil.sDateFormat_1));
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setFont(headerFont);
        return cellStyle;
    }
}
