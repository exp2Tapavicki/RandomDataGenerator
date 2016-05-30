//package com.afowd.jasperreports.util;
//
//import ar.com.fdvs.dj.core.DJConstants;
//import ar.com.fdvs.dj.core.DynamicJasperHelper;
//import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
//import ar.com.fdvs.dj.domain.DynamicReport;
//import ar.com.fdvs.dj.domain.Style;
//import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
//import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
//import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
//import ar.com.fdvs.dj.domain.builders.StyleBuilder;
//import ar.com.fdvs.dj.domain.constants.*;
//import ar.com.fdvs.dj.domain.constants.Font;
//import ar.com.fdvs.dj.domain.constants.Transparency;
//import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
//import com.afowd.constants.Const;
//import com.afowd.util.RandomUtil;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRExporter;
//import net.sf.jasperreports.engine.JRExporterParameter;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.export.JRPdfExporter;
//import net.sf.jasperreports.view.JasperViewer;
//import org.apache.log4j.Logger;
//
//import java.awt.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.math.BigInteger;
//import java.util.Collection;
//
//public class JasperReportsUtil {
//	final static Logger logger = Logger.getLogger(JasperReportsUtil.class);
//	private static String FILE_NAME_PDF = "pdf/test.pdf";
//	// private static String FILE_NAME_XSL = "xls_xlsx/jasperTest.xlsx";
//	private static DynamicReportBuilder report = new DynamicReportBuilder();
//	private static AbstractColumn abstractColumn = null;
//	private static String sNameOfTheRow = null;
//	private static String sNameOfTheProperty = null;
//	private static JasperPrint jasperPrint = null;
//	private static Style headerStyle = createHeaderStyle();
//	private static Style detailTextStyle = createDetailTextStyle();
//	private static Style detailNumberStyle = createDetailNumberStyle();
//
//	/**
//	 * create jasper report for Collection of data
//	 *
//	 * @param alData
//	 */
//	public static void getReport(Collection<Object> alData) {
//		if (alData != null && !alData.isEmpty()) {
//			genereteJasperPrint(alData);
//			JasperViewer jasperViewer = new JasperViewer(jasperPrint);
//			jasperViewer.setVisible(true);
//
//		}
//	}
//
//	/**
//	 * generetes jasper print
//	 *
//	 * @param alData
//	 */
//	private static void genereteJasperPrint(Collection<Object> alData) {
//		try {
//			getReport(alData.iterator().next(), headerStyle, detailTextStyle, Boolean.FALSE, null, null);
//			setReportStyle(alData.iterator().next());
//
//			DynamicReport dynaReport = report.build();
//			jasperPrint = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(), new JRBeanCollectionDataSource(alData, false));
//		} catch (JRException e) {
//			logger.error(e);
//		}
//
//	}
//
//	/**
//	 * export data to pdf
//	 *
//	 * @param alData
//	 */
//	public static void exportToPDF(Collection<Object> alData) {
//        genereteJasperPrint(alData);
//			JRExporter exporter = new JRPdfExporter();
//			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, FILE_NAME_PDF);
//        try {
//            exporter.exportReport();
//        } catch (JRException e) {
//            e.printStackTrace();
//        }
//
////        JRPdfExporter exporter1 = new JRPdfExporter();
////        exporter1.setExporterInput(new SimpleExporterInput(jasperPrint));
////        OutputStreamExporterOutput outputStreamExporterOutput = new OutputStreamExporterOutput() {
////            @Override
////            public OutputStream getOutputStream() {
////                OutputStream outputStream = null;
////                try {
////                    outputStream = new FileOutputStream(new File(FILE_NAME_PDF));
////                } catch (FileNotFoundException e) {
////                    e.printStackTrace();
////                }
////
////                return outputStream;
////            }
////
////            @Override
////            public void close() {
////                try {
////                    getOutputStream().close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        };
////        exporter1.setExporterOutput(outputStreamExporterOutput);
////        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
////        exporter1.setConfiguration(configuration);
//
//
//    }
//
//	/**
//	 * export data to XSL
//	 *
//	 * @param alData
//	 */
//	// public static void exportToXSL(Collection<Object> alData) {
//	// try {
//	// genereteJasperPrint(alData);
//	//
//	// JRXlsExporter exporterxls2 = new JRXlsExporter();
//	// exporterxls2.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
//	// Boolean.TRUE);
//	// exporterxls2.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
//	// Boolean.TRUE);
//	// exporterxls2.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
//	// Boolean.TRUE);
//	// exporterxls2.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
//	// Boolean.FALSE);
//	// exporterxls2.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
//	// Boolean.TRUE);
//	// exporterxls2.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
//	// Boolean.FALSE);
//	// exporterxls2.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS,
//	// Boolean.FALSE);
//	// exporterxls2.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//	// exporterxls2.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
//	// FILE_NAME_XSL);
//	// exporterxls2.exportReport();
//	// } catch (JRException e) {
//	// logger.error(e);
//	// }
//	// }
//	/**
//	 * set report style
//	 *
//	 * @param object
//	 */
//	private static void setReportStyle(Object object) {
//		StyleBuilder titleStyle = new StyleBuilder(true);
//		titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
//		titleStyle.setFont(new Font(20, Font._FONT_GEORGIA, true));
//
//		StyleBuilder subTitleStyle = new StyleBuilder(true);
//		subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
//		subTitleStyle.setFont(new Font(15, Font._FONT_GEORGIA, true));
//
//		report.setPageSizeAndOrientation(Page.Page_Letter_Landscape());
//		report.setWhenResourceMissing(DJConstants.WHEN_RESOURCE_MISSING_TYPE_EMPTY);
//		report.setTitle(object.getClass().getSimpleName() + " Report");
//		report.setTitleStyle(titleStyle.build());
//		report.setSubtitle(object.getClass().getSimpleName() + " Subtitle");
//		report.setSubtitleStyle(subTitleStyle.build());
//	}
//
//	/**
//	 * set header style
//	 *
//	 * @return
//	 */
//	private static Style createHeaderStyle() {
//		StyleBuilder sb = new StyleBuilder(true);
//		sb.setFont(Font.VERDANA_MEDIUM_BOLD);
//		sb.setBorderBottom(Border.THIN());
//		sb.setBorderTop(Border.THIN());
//		sb.setBorderLeft(Border.THIN());
//		sb.setBorderRight(Border.THIN());
//		sb.setBorderBottom(Border.PEN_2_POINT());
//		sb.setBorderColor(Color.BLACK);
//		sb.setBackgroundColor(Color.ORANGE);
//		sb.setTextColor(Color.BLACK);
//		sb.setHorizontalAlign(HorizontalAlign.CENTER);
//		sb.setVerticalAlign(VerticalAlign.MIDDLE);
//		sb.setTransparency(Transparency.OPAQUE);
//		sb.setStretching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
//		return sb.build();
//	}
//
//	/**
//	 * set Detail text style
//	 *
//	 * @return
//	 */
//	private static Style createDetailTextStyle() {
//		StyleBuilder sb = new StyleBuilder(true);
//		sb.setFont(Font.VERDANA_MEDIUM);
//		sb.setBorderBottom(Border.THIN());
//		sb.setBorderTop(Border.THIN());
//		sb.setBorderLeft(Border.THIN());
//		sb.setBorderRight(Border.THIN());
//		sb.setBorderColor(Color.BLACK);
//		sb.setTextColor(Color.BLACK);
//		sb.setHorizontalAlign(HorizontalAlign.LEFT);
//		sb.setVerticalAlign(VerticalAlign.MIDDLE);
//		sb.setPaddingLeft(5);
//		sb.setStretching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
//		return sb.build();
//	}
//
//	/**
//	 * set Detail number style
//	 *
//	 * @return
//	 */
//	private static Style createDetailNumberStyle() {
//		StyleBuilder sb = new StyleBuilder(true);
//		sb.setFont(Font.VERDANA_MEDIUM);
//		sb.setBorderBottom(Border.THIN());
//		sb.setBorderTop(Border.THIN());
//		sb.setBorderLeft(Border.THIN());
//		sb.setBorderRight(Border.THIN());
//		sb.setBorderColor(Color.BLACK);
//		sb.setTextColor(Color.BLACK);
//		sb.setHorizontalAlign(HorizontalAlign.RIGHT);
//		sb.setVerticalAlign(VerticalAlign.MIDDLE);
//		sb.setPaddingRight(5);
//		sb.setStretching(Stretching.RELATIVE_TO_TALLEST_OBJECT);
//		return sb.build();
//	}
//
//	private static AbstractColumn createColumn(String property, Class<?> type, String title, int width, Style headerStyle, Style detailStyle) throws ColumnBuilderException {
//		return ColumnBuilder.getInstance().setColumnProperty(property, type.getName()).setTitle(title).setWidth(Integer.valueOf(width)).setStyle(detailStyle)
//				.setHeaderStyle(headerStyle).build();
//	}
//
//	private static void getReport(Object object, Style headerStyle, Style detailTextStyle, Boolean bInnerData, String sBeanName, String sParent) {
//		try {
//			Method[] methods = ((Object) object).getClass().getDeclaredMethods();
//			for (Method method : methods) {
//				if (method.getName().startsWith(Const.STRING_GET)) {
//					abstractColumn = null;
//					if (sBeanName == null) {
//						sNameOfTheRow = object.getClass().getSimpleName()
//								+ Const.COMMA_DELIMITER
//								+ (method.getName().substring(Const.THREE, method.getName().length())).substring(Const.ZERO, Const.ONE).toLowerCase()
//								.concat(method.getName().substring(Const.FOUR, method.getName().length()));
//						sNameOfTheProperty = (method.getName().substring(Const.THREE, method.getName().length())).substring(Const.ZERO, Const.ONE).toLowerCase()
//								.concat(method.getName().substring(Const.FOUR, method.getName().length()));
//					} else {
//						sNameOfTheRow = sParent
//								+ Const.COMMA_DELIMITER
//								+ sBeanName
//								+ Const.COMMA_DELIMITER
//								+ (method.getName().substring(Const.THREE, method.getName().length())).substring(Const.ZERO, Const.ONE).toLowerCase()
//								.concat(method.getName().substring(Const.FOUR, method.getName().length()));
//						sNameOfTheProperty = sBeanName.substring(Const.ZERO, Const.ONE).toLowerCase().concat(sBeanName.substring(Const.ONE, sBeanName.length()))
//								+ Const.COMMA_DELIMITER
//								+ (method.getName().substring(Const.THREE, method.getName().length())).substring(Const.ZERO, Const.ONE).toLowerCase()
//								.concat(method.getName().substring(Const.FOUR, method.getName().length()));
//					}
//					Class<?> returnType = method.getReturnType();
//					if (!RandomUtil.listOfBasicTypes.contains(returnType.getName()) && !RandomUtil.listOfHashTypes.contains(returnType.getName())
//							&& !RandomUtil.listOfArrayTypes.contains(returnType.getName()) && !bInnerData) {
//						getReport(method.invoke(object), headerStyle, detailTextStyle, Boolean.TRUE, (method.getName().substring(Const.THREE, method.getName().length())), object
//								.getClass().getSimpleName());
//					} else if (RandomUtil.listOfHashTypes.contains(returnType.getName()) || RandomUtil.listOfArrayTypes.contains(returnType.getName())) {
//						// abstractColumn = createColumn(sNameOfTheProperty,
//						// returnType, sNameOfTheRow, 30, headerStyle,
//						// detailTextStyle);
//					} else if (RandomUtil.listOfBasicTypes.contains(returnType.getName()) && !RandomUtil.mMappingMap.containsKey(returnType.getSimpleName())
//							&& !returnType.getName().equals(BigInteger.class.getName()) && !returnType.getName().equals(Character.class.getName())) {
//						abstractColumn = createColumn(sNameOfTheProperty, returnType, sNameOfTheRow, 30, headerStyle, detailTextStyle);
//					} else if (RandomUtil.mMappingMap.containsKey(returnType.getSimpleName()) && !returnType.getName().equals(Character.class.getName())
//							&& !returnType.getName().equals(char.class.getName())) {
//						Class<?> theClass = null;
//						try {
//							theClass = Class.forName(RandomUtil.mMappingMap.get(returnType.getSimpleName()));
//						} catch (ClassNotFoundException e) {
//							logger.error(e);
//						}
//						abstractColumn = createColumn(sNameOfTheProperty, theClass, sNameOfTheRow, 30, headerStyle, detailTextStyle);
//					} else if (returnType.getName().equals(BigInteger.class.getName())) {
//						// abstractColumn = createColumn(sNameOfTheProperty,
//						// BigInteger.class, sNameOfTheRow, 30, headerStyle,
//						// detailTextStyle);
//					} else if (returnType.getName().equals(Character.class.getName()) || returnType.getName().equals(char.class.getName())) {
//						// abstractColumn = createColumn(sNameOfTheProperty,
//						// String.class, sNameOfTheRow, 30, headerStyle,
//						// detailTextStyle);
//					}
//					if (abstractColumn != null) {
//						report.addColumn(abstractColumn);
//					}
//				}
//			}
//		} catch (IllegalArgumentException | ColumnBuilderException | InvocationTargetException | IllegalAccessException e) {
//			logger.error(e);
//		}
//    }
//}
