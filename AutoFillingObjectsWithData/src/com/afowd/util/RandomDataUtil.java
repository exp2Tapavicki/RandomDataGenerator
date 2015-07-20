package com.afowd.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.afowd.boundary.Boundary;
import com.afowd.boundary.BoundaryManager;
import com.afowd.constants.Const;
import com.afowd.internationalizationManager.InternationalizationManager;
import com.afowd.range.filter.RangeNumberFilter;

public class RandomDataUtil {
	final static Logger logger = Logger.getLogger(RandomDataUtil.class);
	private static HashMap<String, ArrayList<Object>> hmFilledClasses = new HashMap<String, ArrayList<Object>>();
	private static HashMap<Integer, HashSet<String>> hmClassesPerDeepLevel = new HashMap<Integer, HashSet<String>>();
	private static HashSet<String> hsOrmClassesForFill = new HashSet<String>();
	private static int numberOfData = Const.DEAFULT_NUMBER_OF_DATA;
	private static int numberOfDataForOneToManyRelation = Const.DEAFULT_NUMBER_OF_DATA_FOR_ONE_TO_MANY_RELATION;
	private static int deepLevel = Const.TWO;
	private static HashMap<String, Boundary> hmBoundary = new HashMap<String, Boundary>();
	private static boolean bAllowNulls = Boolean.TRUE;
	private static int precision = Const.FIVE;
	private static String sDatePattern = "([0-9]{2}).([0-9]{2}).([0-9]{4})";
	private static Pattern pDatePattern = Pattern.compile(sDatePattern);
	private static Matcher matcher = null;
	private static String sPackageFromClass = null;
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

	public static void setNumberOfDataForOneToManyRelation(
			int numberOfDataForOneToManyRelation) {
		RandomDataUtil.numberOfDataForOneToManyRelation = numberOfDataForOneToManyRelation;
	}

	public static int getDeepLevel() {
		return deepLevel;
	}

	public static void setDeepLevel(int deepLevel) {
		RandomDataUtil.deepLevel = deepLevel;
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
		rangeNumberFilter.setIsInRange(RandomUtil.getRandomBoolean(Boolean.FALSE));
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
		hmFilledClasses = new HashMap<String, ArrayList<Object>>();
		tfu = new ThreadFactoryUtil();
		tfu.setDaemon(true);
		tfu.setPriority(Thread.NORM_PRIORITY);
		tfu.setStackSize(1024);
		tfu.setThreadGroup(new ThreadGroup("ClassesForFill"));
		tfu.setWrapRunnable(true);
		
		if (sClassName != null) {
			findOutBoundaries();
			getFilledBasicData(sClassName);
			
			if (deepLevel > 0) {
				tfu.setiPriorityThreadLevel(Const.ZERO);
				getFilledBasicDataForRelationClasses(sClassName);
				tfu.setiPriorityThreadLevel(Const.ONE_INTEGER);
				getFilledRelationsForClasses(sClassName);
				HashMap<Integer, ArrayList<Thread>>  hmThreads = tfu.getHmThreads();
				
				for (Iterator<?> iterator = hmThreads.entrySet().iterator(); iterator.hasNext();) {
				    Entry<Integer, ArrayList<Thread>> entry = (Entry<Integer, ArrayList<Thread>>) iterator.next();
				    ArrayList<Thread> alThreads = entry.getValue();
				    for (Iterator<Thread> iterator2 = alThreads.iterator(); iterator2.hasNext();) {
						Thread thread = (Thread) iterator2.next();
						thread.start();
					}
				    for (Iterator<Thread> iterator2 = alThreads.iterator(); iterator2.hasNext();) {
						Thread thread = (Thread) iterator2.next();
						try {
							thread.join();
						} catch (InterruptedException e) {
							logger.error(e);
						}
					}
				}
			}
			if (hmFilledClasses.get(sClassName) != null && !hmFilledClasses.get(sClassName).isEmpty()){
				return (ArrayList<Object>) hmFilledClasses.get(sClassName);
			} else {
				logger.error(InternationalizationManager.getString(Const.CLASS_NOT_EXIST)+ "[" + sClassName + "] " + InternationalizationManager.getString(Const.IN_METHOD) + " getFilledData(String sClassName)" );
				return null;
			}
		} else {
			logger.error(InternationalizationManager.getString(Const.CLASS_NOT_EXIST)+ "["   + sClassName + "] " + InternationalizationManager.getString(Const.IN_METHOD) + " getFilledData(String sClassName)" );
			return null;
		}
		
	}
	
	/**
	 * loads Boundaries from properties file or find out max min values for types and put it in hash map for lather use
	 */
	private static void findOutBoundaries() {
		HashMap<String, String> hmTemp = BoundaryManager.getAllBoundaries();
		Iterator<Map.Entry<String,String>> itrator = hmTemp.entrySet().iterator();
		Boundary boundary = null;
		while(itrator.hasNext()) {
		    Map.Entry<String,String> entry = itrator.next();
		    String sKey = entry.getKey().toString();
		    String sValue = entry.getValue();
		    String[] sClassPropertySplited = sKey.split("\\.");
		    String sClassName = sClassPropertySplited[Const.ZERO];
		    String sClassProperty = sClassPropertySplited[Const.ONE];
		    String[] sPropertyValues = sValue.split(",");
		    String sReturnType = getReturnParamFromMethod(sClassName, sClassProperty).getName();
		    boundary = new Boundary();
		    
		    if (sPropertyValues != null){
		    	switch (sPropertyValues.length) {
				case 1:
					boundary.setObjMin(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues[Const.ZERO].trim()))); 
					break;
				case 2:
					matcher = pDatePattern.matcher(sPropertyValues[Const.ZERO].trim());
					if (sReturnType.equals(Date.class.getName()) && matcher.matches()){
						String day = matcher.group(1);
						String month = matcher.group(2);
						String year = matcher.group(3);
						String sTempDateValue = month + "." + day + "." + year;
						sTempDateValue = sTempDateValue.trim().replace(".", "/");
						boundary.setObjMin(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sTempDateValue)));
					} else {
						boundary.setObjMin(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues[Const.ZERO].trim())));
					}
					
					matcher = pDatePattern.matcher(sPropertyValues[Const.ONE].trim());
					if (sReturnType.equals(Date.class.getName()) && matcher.matches()){
						String day = matcher.group(1);
						String month = matcher.group(2);
						String year = matcher.group(3);
						String sTempDateValue = month + "." + day + "." + year;
						sTempDateValue = sTempDateValue.trim().replace(".", "/");
						boundary.setObjMax(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sTempDateValue)));
					} else {
						boundary.setObjMax(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues[Const.ONE].trim())));
					}
					
					break;
				case 3:
					boundary.setObjMin(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues[Const.ZERO].trim())));
					boundary.setObjMax(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues[Const.ONE].trim())));
					boundary.setbAllowNullsPom(Boolean.valueOf(sPropertyValues[Const.TWO].trim().toString()).booleanValue());
					break;
				case 4:
					boundary.setObjMin(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues[Const.ZERO].trim())));
					boundary.setObjMax(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues[Const.ONE].trim())));
					boundary.setbAllowNullsPom(Boolean.valueOf(sPropertyValues[Const.TWO].trim().toString()).booleanValue());
					boundary.setObjPrecision(getDinamiclyCastWithValue(sReturnType, checkMinimumMaximumLimitForClass(sReturnType, sPropertyValues[Const.THREE].trim())));
					break;
				default:
					break;
				}
		    	hmBoundary.put(entry.getKey(), boundary);
		    }
		}
		
		List<String> lBasicType = RandomUtil.listOfBasicTypes;
		for (Iterator<String> iterator = lBasicType.iterator(); iterator.hasNext();) {
			String sBasicType = (String) iterator.next();
			boundary = new Boundary();
			boundary.setbAllowNullsPom(bAllowNulls);
			boundary.setObjPrecision(precision);
			getMinimumMaximumLimitForClass(sBasicType, boundary);
			if (!RandomUtil.mMappingMap.containsKey(sBasicType)){
				hmBoundary.put(sBasicType, boundary);
			} else {
				hmBoundary.put(RandomUtil.mMappingMap.get(sBasicType), boundary);
			}
		}
	}

	/**
	 * fill all relation for classes
	 * @param sClassName
	 */
	private static void getFilledRelationsForClasses(String sClassName) {
		for (final String sClasses : hsOrmClassesForFill) {
			runnable = new Runnable() {
				public void run() {
					getFilledRelationsForData(sClasses);
				}
			};
			tfu.setsClassName(sClasses);
			tfu.newThread(runnable);
		}
	}

	/**
	 * Fill basic data of specific Class (int, float, char, Integer, Bigdecimal, String...)
	 * @param sClassName
	 * @return
	 */
	public static void getFilledBasicData(String sClassName) {
		for (int i = 0; i < getTempNumberOfData(sClassName); i++) {
			Object obj = getClassIntance(sClassName);
			Method[] methods = getClassMethods(sClassName);
			if (obj != null && methods != null && methods.length >= 1) {
				Object object = getFilledBasicData(obj, methods, i); 
				fillHashMapWithObjects(sClassName, object);
			}
		} 
	}
	
	/**
	 * Fill relation classes with basic data
	 * @param sClassNamesss
	 */
	public static void getFilledBasicDataForRelationClasses (String sClassName){
		HashSet<String> hsLevelFill = new HashSet<String>();
		hsOrmClassesForFill.add(sClassName);
		hsLevelFill.add(sClassName);
		hmClassesPerDeepLevel.put(Integer.valueOf(Const.ZERO_INTEGER), (HashSet<String>)hsLevelFill);
		for (int i = 0; i < deepLevel; i++) {
			hsLevelFill = new HashSet<String>();
			for (String sClassNameForDeep : hmClassesPerDeepLevel.get(Integer.valueOf(i))) {
				getAllClassesForRelationsFill(sClassNameForDeep, hsLevelFill);
			}
			hmClassesPerDeepLevel.put(Integer.valueOf(i+1), (HashSet<String>)hsLevelFill);
		}
		for (final String sClassesName : hsOrmClassesForFill) {
			if (!hmFilledClasses.containsKey(sClassesName)){
				runnable = new Runnable() {
					public void run() {
						for (int i = 0; i < numberOfDataForOneToManyRelation; i++) {
							Object obj = getClassIntance(sClassesName);
							Method[] methods = getClassMethods(sClassesName);
							if (obj != null && methods != null && methods.length >= 1) {
								fillHashMapWithObjects(sClassesName, getFilledBasicData(obj, methods, i));
							}
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
	 * @param sClassName
	 */
	public static void getFilledRelationsForData(String sClassName) {
		ArrayList<Object> alData = hmFilledClasses.get(sClassName);
		if (alData != null && !alData.isEmpty()){
			for (int i = 0; i < getTempNumberOfData(sClassName); i++) {
				Object object = alData.get(i);
				Method[] methods = object.getClass().getMethods();
				if (object != null && methods != null && methods.length >= 1) {
					for (int j = 0; j < methods.length; j++) {
						Method method = methods[j];
						String sMethodName = method.getName();
						Object objectForSet = null;
						if (sMethodName.toLowerCase().startsWith(Const.STRING_SET)) {
							String sParameterType = getFirstMethodParameterType(method);
							if (hmFilledClasses.containsKey(sParameterType)) {
								objectForSet = ((ArrayList<Object>)hmFilledClasses.get(sParameterType)).get(RandomUtil.getRandomPrimitiveInt(Const.ZERO, getTempNumberOfData(sParameterType) - Const.ONE));
								invokeMethodOnObject(object, method, objectForSet);								
							} else if (RandomUtil.listOfArrayTypes.contains(sParameterType)){
								if (!RandomUtil.listOfBasicTypes.contains(getFirstActualTypeArgumentClass(method.getGenericParameterTypes())) &&
										!RandomUtil.listOfArrayTypes.contains(getFirstActualTypeArgumentClass(method.getGenericParameterTypes())) &&
											!RandomUtil.listOfHashTypes.contains(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()))){
									objectForSet = getRandomDataOfFilledClasses(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()));
									invokeMethodOnObject(object, method, objectForSet);
								}
							} else if (RandomUtil.listOfHashTypes.contains(sParameterType)){
								Vector<Class<?>> vectorParams = getHashActualTypeArgumentsClass(method.getGenericParameterTypes());
								if (vectorParams != null){
									if (vectorParams != null && !vectorParams.isEmpty()){
										Vector<Object> vector = new Vector<Object>();
										HashMap<Object, Object> hm = new HashMap<Object, Object>();
										for (int k = 0; k < getTempNumberOfData(sParameterType); k++) {
											vector = new Vector<Object>();
											for (int l = 0; l < vectorParams.size(); l++) {
												String sParam = vectorParams.get(l).getName();
												if (RandomUtil.listOfBasicTypes.contains(sParam)){
													vector.add(getRandomBasicObjectValue(sParam, null, sClassName));
												} else {
													vector.add((Object)hmFilledClasses.get(sParam).get(RandomUtil.getRandomPrimitiveInt(Const.ZERO, getTempNumberOfData(sParam) - Const.ONE)));
												}
											}
											hm.put(vector.get(0), vector.get(1));
										}
										invokeMethodOnObject(object, method, hm);
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
	 * @param sParameterType
	 * @return
	 */
	private static ArrayList<Object> getRandomDataOfFilledClasses(String sParameterType) {
		ArrayList<Object> alData = new ArrayList<Object>();
		for (int i = 0; i < getTempNumberOfData(sParameterType); i++) {
			alData.add(((ArrayList<Object>)hmFilledClasses.get(sParameterType)).get(RandomUtil.getRandomPrimitiveInt(Const.ZERO, getTempNumberOfData(sParameterType) - Const.ONE)));
		}
		return alData;
	}
	/**
	 * get actual number of current data
	 * @param sParameterType
	 * @return
	 */
	private static int getTempNumberOfData(String sClassName) {
		if (sClassName.equals(RandomDataUtil.sClassName)){
			return numberOfData;
		} else {
			return numberOfDataForOneToManyRelation;
		}
	}

	/**
	 *  get all classes for filling relations
	 * @param sClassName
	 * @param hsOrmClassesForFill HashSet of names of classes to fill
	 */
	private static void getAllClassesForRelationsFill(String sClassName, HashSet<String> hsTempClassesForFill) {
		Object obj = getClassIntance(sClassName);
		Method[] methods = getClassMethods(sClassName);
		if (obj != null && methods != null && methods.length >= 1) {
			for (int j = 0; j < methods.length; j++) {
				Method method = methods[j];
				String sMethodName = method.getName();
				if (sMethodName.toLowerCase().startsWith(Const.STRING_SET)) {
					String sParameterType = getFirstMethodParameterType(method);
					if (!RandomUtil.listOfBasicTypes.contains(sParameterType) && !RandomUtil.listOfHashTypes.contains(sParameterType) && !RandomUtil.listOfArrayTypes.contains(sParameterType)){
						if (!hsOrmClassesForFill.contains(sParameterType)){
							hsOrmClassesForFill.add(sParameterType);
							hsTempClassesForFill.add(sParameterType);
						}
					} else if (!RandomUtil.listOfBasicTypes.contains(sParameterType) && !RandomUtil.listOfHashTypes.contains(sParameterType) && RandomUtil.listOfArrayTypes.contains(sParameterType)){
						String sParam = getFirstActualTypeArgumentClass(method.getGenericParameterTypes());
						if (!RandomUtil.listOfBasicTypes.contains(sParam) && !RandomUtil.listOfHashTypes.contains(sParam) && !RandomUtil.listOfArrayTypes.contains(sParam)){
							if (!hsOrmClassesForFill.contains(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()))){
								hsOrmClassesForFill.add(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()));
								hsTempClassesForFill.add(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()));
							}
						}
					} else if (!RandomUtil.listOfBasicTypes.contains(sParameterType) && RandomUtil.listOfHashTypes.contains(sParameterType) && !RandomUtil.listOfArrayTypes.contains(sParameterType)){
						Vector<Class<?>> vectorParams = getHashActualTypeArgumentsClass(method.getGenericParameterTypes());
						if (vectorParams != null){
							if (vectorParams != null && !vectorParams.isEmpty()){
								for (int i = 0; i < vectorParams.size(); i++) {
									String sParam = vectorParams.get(i).getName();
									if (!RandomUtil.listOfBasicTypes.contains(sParam) && !RandomUtil.listOfHashTypes.contains(sParam) && !RandomUtil.listOfArrayTypes.contains(sParam)){
										if (!hsOrmClassesForFill.contains(sParam)){
											hsOrmClassesForFill.add(sParam);
											hsTempClassesForFill.add(sParam);
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
	 * @param obj
	 * @param methods
	 * @param i
	 * @return
	 */
	private static Object getFilledBasicData(Object obj, Method[] methods, int i) {
		if (obj != null) {
			for (int j = 0; j < methods.length; j++) {
				Method method = methods[j];
				String sMethodName = method.getName();
				if (sMethodName.toLowerCase().startsWith(Const.STRING_SET)) {
					String sParameterType = getFirstMethodParameterType(method);
					if (sParameterType != null){
						if ( RandomUtil.listOfBasicTypes.contains(sParameterType)) {
							if (Const.STRING_SET_ID.equalsIgnoreCase(sMethodName)){
								invokeMethodOnObject(obj, method, getDinamiclyCastWithValue(sParameterType, String.valueOf(i)));
							} else {
								invokeMethodOnObject(obj, method, getRandomBasicObjectValue(sParameterType, sMethodName, obj.getClass().getName()));	
							}
						} else if (RandomUtil.listOfArrayTypes.contains(sParameterType) && RandomUtil.listOfBasicTypes.contains(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()))) {
							ArrayList<Object> alTempData = new ArrayList<Object>();
							for (int k = 0; k < getTempNumberOfData(sParameterType); k++) {
								alTempData.add(getRandomBasicObjectValue(getFirstActualTypeArgumentClass(method.getGenericParameterTypes()), sMethodName, obj.getClass().getName()));
							}
							invokeMethodOnObject(obj, method, alTempData);
						} else if (RandomUtil.listOfHashTypes.contains(sParameterType)) {
							Vector<Class<?>> vectorParams = getHashActualTypeArgumentsClass(method.getGenericParameterTypes());
							if (vectorParams != null && !vectorParams.isEmpty()){
								Vector<Object> vector = new Vector<Object>();
								HashMap<Object, Object> hm = new HashMap<Object, Object>();
								for (int k = 0; k < getTempNumberOfData(sParameterType); k++) {
									vector = new Vector<Object>();
									for (int l = 0; l < vectorParams.size(); l++) {
										if (RandomUtil.listOfBasicTypes.contains(vectorParams.get(l).getName())){
											vector.add(getRandomBasicObjectValue(vectorParams.get(l).getName(), false, null, obj.getClass().getName()));
										} else {
											if (!hmFilledClasses.containsKey(vectorParams.get(l).getName())){
												getFilledBasicData(vectorParams.get(l).getName()); 
											}
											Object object = (Object)hmFilledClasses.get(vectorParams.get(l).getName()).get(RandomUtil.getRandomPrimitiveInt(Const.ZERO, getTempNumberOfData(vectorParams.get(l).getName()) -Const.ONE));
											vector.add(object);
										}
									}
									hm.put(vector.get(0), vector.get(1));
								}
								invokeMethodOnObject(obj, method, hm);
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
	 * @param sParameterType
	 * @param value
	 * @return
	 */
	private static String checkMinimumMaximumLimitForClass(String sParameterType, String sValue) {
		Class<?> theClass = null;
		try {
			if (!RandomUtil.mMappingMap.containsKey(sParameterType)){
				theClass = Class.forName(sParameterType);
			} else {
				theClass =  Class.forName(RandomUtil.mMappingMap.get(sParameterType));
			}
			if (sValue != null && !theClass.getName().equals(BigDecimal.class.getName()) && !theClass.getName().equals(BigInteger.class.getName()) && !theClass.getName().equals(Character.class.getName())
					&& !theClass.getName().equals(Date.class.getName()) && !theClass.getName().equals(Boolean.class.getName())){
				Field[] fields = theClass.getDeclaredFields();
				for (Field field : fields) {
					if (field.getName().equals(Const.MIN_VALUE)){
						if (new BigDecimal(sValue).doubleValue() <  new BigDecimal(field.get(Const.MIN_VALUE).toString()).doubleValue()){
							sValue = field.get(Const.MIN_VALUE).toString();
						}
					} else if (field.getName().equals(Const.MAX_VALUE)){
						if (new BigDecimal(sValue).doubleValue()> new BigDecimal(field.get(Const.MAX_VALUE).toString()).doubleValue()){
							sValue = field.get(Const.MAX_VALUE).toString();
						} 
					}
				}
			} else if (theClass.getName().equals(Character.class.getName())){
				if (Integer.parseInt(sValue) < Const.ZERO_INTEGER) {
					sValue = (Const.ZERO_INTEGER).toString();
				} else if (Integer.parseInt(sValue) > Const.MAX_CHAR) {
					sValue = Integer.valueOf(Const.MAX_CHAR).toString();
				}
			}
		} catch (ClassNotFoundException e) {
			logger.error(e);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		}
		return sValue;
	}
	
	/**
	 * check if max value is bigger then maximum if it is then set it to maximum
	 * @param sParameterType
	 * @param value
	 * @return
	 */
	private static void getMinimumMaximumLimitForClass(String sParameterType, Boundary boundary) {
		Class<?> theClass = null;
		try {
			if (!RandomUtil.mMappingMap.containsKey(sParameterType)){
				theClass = Class.forName(sParameterType);
			} else {
				theClass =  Class.forName(RandomUtil.mMappingMap.get(sParameterType));
			}
			if (!theClass.getName().equals(BigDecimal.class.getName()) && !theClass.getName().equals(BigInteger.class.getName()) && !theClass.getName().equals(Character.class.getName())
					&& !theClass.getName().equals(Date.class.getName()) && !theClass.getName().equals(Boolean.class.getName())){
				Field[] fields = theClass.getDeclaredFields();
				for (Field field : fields) {
					if (field.getName().equals(Const.MIN_VALUE)){
						boundary.setObjMin(getDinamiclyCastWithValue(sParameterType, field.get(Const.MIN_VALUE).toString()));
					} else if (field.getName().equals(Const.MAX_VALUE)){
						boundary.setObjMax(getDinamiclyCastWithValue(sParameterType, field.get(Const.MAX_VALUE).toString()));
					}
				}
			} else if (theClass.getName().equals(BigDecimal.class.getName())){
				if (boundary.getObjMin() == null){
					boundary.setObjMin(Const.MIN_VALUE_BIGDECIMAL);
				}
				if (boundary.getObjMax() == null){
					boundary.setObjMax(Const.MAX_VALUE_BIGDECIMAL);
				}
				if (boundary.getObjPrecision() == null){
					boundary.setObjPrecision(precision);
				}
			} else if (theClass.getName().equals(BigInteger.class.getName())){
				if (boundary.getObjMin() == null){
					boundary.setObjMin(Const.MIN_VALUE_BIGINTEGER);
				}
				if (boundary.getObjMax() == null){
					boundary.setObjMax(Const.MAX_VALUE_BIGINTEGER);
				}
			} else if (theClass.getName().equals(Character.class.getName())){
				if (boundary.getObjMin() == null){
					boundary.setObjMin(getDinamiclyCastWithValue(Integer.class.getName().toString(), (Const.ZERO_INTEGER).toString()));
				}
				if (boundary.getObjMax() == null){
					boundary.setObjMax(getDinamiclyCastWithValue(Integer.class.getName().toString(), (Integer.valueOf(Const.MAX_CHAR)).toString()));
				}
			} else if (theClass.getName().equals(Date.class.getName())){
				if (boundary.getObjMin() == null){
					long minDate = RandomUtil.getMinimumDate();
					boundary.setObjMin(new Date(minDate));
				}
				if (boundary.getObjMax() == null){
					long maxDate = RandomUtil.getMaximumDate();
					boundary.setObjMax(new Date(maxDate));
				}
			}
		} catch (ClassNotFoundException e) {
			logger.error(e);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		}
	}

	/**
	 * dynamically creates object with value from name of the class and value via constructor example new Integer("value"); new sClassName(i);
	 * @param sClassName
	 * @param value
	 * @return
	 * @throws NoSuchFieldException 
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private static Object getDinamiclyCastWithValue(String sClassName, String value) {
		Object object = null;
		Class<?> theClass = null;
		try {
			if (Character.class.getName().equals(sClassName) || char.class.getName().equals(sClassName)){
				theClass = Integer.class;
			} else if (!RandomUtil.mMappingMap.containsKey(sClassName)){
				theClass = Class.forName(sClassName);
			} else {
				theClass =  Class.forName(RandomUtil.mMappingMap.get(sClassName));
			}
			
			Class<?> sParamFromClass = null;
			Constructor[] allConstructors = theClass.getDeclaredConstructors();
		    for (Constructor ctor : allConstructors) {
		    	if (sParamFromClass != null && sParamFromClass.getName().equals(String.class.getName())){
					break;
				}
				Class<?>[] paramType  = ctor.getParameterTypes();
				for (int i = 0; i < paramType.length; i++) {
					if (sParamFromClass != null && sParamFromClass.getName().equals(String.class.getName())){
						break;
					}
					sParamFromClass = paramType[i];
					break;
			    }
			}
			Constructor<?> cons = (Constructor<?>) theClass.getConstructor(new Class<?>[]{sParamFromClass});  
			object = (Object) cons.newInstance(new Object[]{value}); 
		} catch (ClassNotFoundException e) {
			logger.error(InternationalizationManager.getString("THERE_IS_NO_CLASS_WITH_THE_NAME") + sClassName, e);
		} catch (NoSuchMethodException e) {
			logger.error(e);
		} catch (SecurityException e) {
			logger.error(e);
		} catch (InstantiationException e) {
			logger.debug(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		}
		return object;
	}

	/**
	 * fill orm classes in hashmap for lather use of filling relations
	 * @param sClassName
	 * @param object
	 */
	private static void fillHashMapWithObjects(String sClassName, Object object) {
		if (hmFilledClasses.containsKey(sClassName) && !hmFilledClasses.get(sClassName).contains(object)){
			hmFilledClasses.get(sClassName).add(object);
		} else if (!hmFilledClasses.containsKey(sClassName)) {
			ArrayList<Object> alList = new ArrayList<Object>();
			alList.add(object);
			hmFilledClasses.put(sClassName, alList);
		}
	}
	
	/**
	 * filling basic types with random data 
	 * @param sClassName
	 * @param sMethodName
	 * @param sSuperClassName
	 * @return
	 */
	private static Object getRandomBasicObjectValue(String sClassName, String sMethodName, String sSuperClassName ){
		return getRandomBasicObjectValue(sClassName, bAllowNulls, sMethodName, sSuperClassName );
	}

	/**
	 * filling basic types with random data 
	 * @param sClassName
	 * @param bAllowNulls
	 * @param sMethodName
	 * @param sSuperClassName
	 * @return
	 */
	private static Object getRandomBasicObjectValue(String sClassName, boolean bAllowNulls, String sMethodName, String sSuperClassName ){
		Boundary boundary = findMinMaxValueForClass(sClassName, sSuperClassName, sMethodName);
		if (sClassName.equals(Integer.class.getName())) {
			return RandomUtil.getRandomInteger((Integer)boundary.getObjMin(), (Integer)boundary.getObjMax(), boundary.isbAllowNullsPom());
		} else if (sClassName.equals(Long.class.getName())) {
			return RandomUtil.getRandomLong((Long)boundary.getObjMin(), (Long)boundary.getObjMax(), boundary.isbAllowNullsPom());
		} else if (sClassName.equals(Short.class.getName())) {
			return RandomUtil.getRandomShort((Short)boundary.getObjMin(), (Short)boundary.getObjMax(), boundary.isbAllowNullsPom());
		} else if (sClassName.equals(Byte.class.getName())) {
			return RandomUtil.getRandomByte((Byte)boundary.getObjMin(), (Byte)boundary.getObjMax(), boundary.isbAllowNullsPom());
		} else if (sClassName.equals(Boolean.class.getName())) {
			return RandomUtil.getRandomBoolean(bAllowNulls);
		} else if (sClassName.equals(Double.class.getName())) {
			return RandomUtil.getRandomDouble((Double)boundary.getObjMin(), (Double)boundary.getObjMax(), new BigDecimal(boundary.getObjPrecision().toString()), boundary.isbAllowNullsPom());
		} else if (sClassName.equals(Float.class.getName())) {
			return RandomUtil.getRandomFloat((Float)boundary.getObjMin(), (Float)boundary.getObjMax(), new BigDecimal(boundary.getObjPrecision().toString()), boundary.isbAllowNullsPom());
		} else if (sClassName.equals(Character.class.getName())) {
			return RandomUtil.getRandomChar((Integer)boundary.getObjMin(), (Integer)boundary.getObjMax());
		} else if (sClassName.equals(String.class.getName())) {
			return getRandomString(sMethodName, bAllowNulls);
		} else if (sClassName.equals(Date.class.getName())) {
			return RandomUtil.getRandomDate((Date)boundary.getObjMin(), (Date)boundary.getObjMax(), bAllowNulls);
		} else if (sClassName.equals(int.class.getName())) {
			return RandomUtil.getRandomPrimitiveInt((Integer)boundary.getObjMin(), (Integer)boundary.getObjMax());
		} else if (sClassName.equals(long.class.getName())) {
			return RandomUtil.getRandomPrimitiveLong((Long)boundary.getObjMin(), (Long)boundary.getObjMax());
		} else if (sClassName.equals(short.class.getName())) {
			return RandomUtil.getRandomPrimitiveShort((Short)boundary.getObjMin(), (Short)boundary.getObjMax());
		} else if (sClassName.equals(byte.class.getName())) {
			return RandomUtil.getRandomPrimitiveByte((Byte)boundary.getObjMin(), (Byte)boundary.getObjMax());
		} else if (sClassName.equals(boolean.class.getName())) {
			return RandomUtil.getRandomBoolean(Boolean.FALSE).booleanValue();
		} else if (sClassName.equals(double.class.getName())) {
			return RandomUtil.getRandomPrimitiveDouble((Double)boundary.getObjMin(), (Double)boundary.getObjMax(), new BigDecimal(boundary.getObjPrecision().toString()));
		} else if (sClassName.equals(float.class.getName())) {
			return RandomUtil.getRandomPrimitiveFloat((Float)boundary.getObjMin(), (Float)boundary.getObjMax(), new BigDecimal(boundary.getObjPrecision().toString()));
		} else if (sClassName.equals(char.class.getName())) {
			return RandomUtil.getRandomChar((Integer)boundary.getObjMin(), (Integer)boundary.getObjMax());
		} else if (sClassName.equals(BigDecimal.class.getName())) {
			return RandomUtil.getRandomBigDecimal((BigDecimal)boundary.getObjMin(), (BigDecimal)boundary.getObjMax(), new BigDecimal(boundary.getObjPrecision().toString()), boundary.isbAllowNullsPom());
		} else if (sClassName.equals(BigInteger.class.getName())) {
			return RandomUtil.getRandomBigInteger((BigInteger)boundary.getObjMin(), (BigInteger)boundary.getObjMax(), boundary.isbAllowNullsPom());
		} 
		return null;
	}
	
	/**
	 * find min max value for boundary for type of Class or Property of the Class
	 * @param sClassName
	 * @param sSuperClassName
	 * @param sMethodName
	 * @return
	 */
	private static Boundary findMinMaxValueForClass(String sClassName, String sSuperClassName, String sMethodName) {
		Boundary boundary = null;
		if (sMethodName != null){
			String sPropertyName = sMethodName.replace(Const.STRING_SET, Const.STRING_EMPTY);
			String sClassProperty = (Character.isUpperCase(sPropertyName.charAt(0)) ? Character.toLowerCase(sPropertyName.charAt(0)) + sPropertyName.substring(1, sPropertyName.length()) : Character.toUpperCase(sPropertyName.charAt(0)) + sPropertyName.substring(1, sPropertyName.length()));
			boundary = hmBoundary.get(sSuperClassName.replace(sPackageFromClass + ".", "") + "." + sClassProperty);
		}
		
		if (boundary == null){
			if (char.class.getName().equals(sClassName)){
				boundary = hmBoundary.get(RandomUtil.mMappingMap.get(sClassName));
			} else {
				boundary = hmBoundary.get(sClassName);
			}
		}
		return boundary;
	}

	/**
	 * return random string depend of method name
	 * @param sMethodName
	 * @param bAllowNulls
	 * @return
	 */
	private static String getRandomString(String sMethodName, boolean bAllowNulls) {
		if (sMethodName != null){
			if (sMethodName.toLowerCase().indexOf(Const.STRING_NAME) > -1 && sMethodName.toLowerCase().indexOf(Const.STRING_FIRST_NAME) == -1 && sMethodName.toLowerCase().indexOf(Const.STRING_LAST_NAME) == -1){
				return RandomUtil.getRandomNames(Const.NAME, bAllowNulls);
			} else if (sMethodName.toLowerCase().indexOf(Const.STRING_FIRST_NAME) > -1){
				return RandomUtil.getRandomNames(Const.FIRST_NAME, bAllowNulls);
			} else if (sMethodName.toLowerCase().indexOf(Const.STRING_LAST_NAME) > -1){
				return RandomUtil.getRandomNames(Const.LAST_NAME, bAllowNulls);
			} else {
				return RandomUtil.getRandomUUID(bAllowNulls);
			}
		} else {
			return RandomUtil.getRandomUUID(bAllowNulls);
		}
	}

	/**
	 * returns first argument type in method
	 * @param method
	 * @return
	 */
	private static String getFirstMethodParameterType(Method method) {
		String sClassName = null;
		Class<?>[] clParams = method.getParameterTypes();
		if (clParams != null) {
			if (clParams.length == 1){
				Class<?> clParam = clParams[0];
				sClassName = clParam.getName();
			}
		}
		return sClassName;
	}
	
	/**
	 * invokes method with arguments on object 
	 * @param obj
	 * @param method
	 * @param objectArgs
	 */
	public static void invokeMethodOnObject(Object obj, Method method, Object objectArgs) {
		try {
			method.invoke(obj, objectArgs);
		} catch (IllegalArgumentException e) {
			logger.error(InternationalizationManager.getString(Const.ILLEGAL_ACESS_EXEPTION_IN_METHOD) + " invokeMethodOnObject(Object obj, Method method, Object objectArgs) ", e);
		} catch (IllegalAccessException e) {
			logger.error(InternationalizationManager.getString(Const.ILLEGAL_ACESS_EXEPTION_IN_METHOD) + "invokeMethodOnObject(Object obj, Method method, Object objectArgs) ", e);
		} catch (InvocationTargetException e) {
			logger.error(InternationalizationManager.getString(Const.ILLEGAL_ACESS_EXEPTION_IN_METHOD) + "invokeMethodOnObject(Object obj, Method method, Object objectArgs) ", e);
		}
	}
	
	/**
	 * returns actual type arguments form Hash type example HashMap<String><Worker> will return Vector who contains String and Worker class name
	 * @param type
	 * @return
	 */
	private static Vector<Class<?>> getHashActualTypeArgumentsClass(Type[] type) {
		Vector<Class<?>> sParameterClass = new Vector<Class<?>>();
		Type t = type[0];
		Type[] typeOfParameters = ((ParameterizedType) t).getActualTypeArguments();
		if (typeOfParameters != null) {
			for (int i = 0; i < typeOfParameters.length; i++) {
				Class<?> clOfParameter = (Class<?>) typeOfParameters[i];
				sParameterClass.add(clOfParameter);
			}
		}
		return sParameterClass;
	}
	
	/**
	 * returns first actual type arguments class name example ArrayList<Worker> returns Worker class name
	 * @param type
	 * @return
	 */
	public static String getFirstActualTypeArgumentClass(Type[] type) {
		if (type.length > 0){
			Type t = type[0];
			Type[] typeOfParameters = ((ParameterizedType) t).getActualTypeArguments();
			if (typeOfParameters != null) {
				Type typeOfParameter = typeOfParameters[0];
				Class<?> clOfParameter = (Class<?>) typeOfParameter;
				return clOfParameter.getName();
			}
		}
		return null;
	}
	
	/**
	 * returns methods from class name
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
	 * @param sClassName
	 * @param sFieldName
	 * @return
	 */
	private static Class<?> getReturnParamFromMethod(String sClassName, String sFieldName) {
		try {
			Class<?> cl = Class.forName(sPackageFromClass + "." +sClassName);
			if (cl != null) {
				return cl.getDeclaredField(sFieldName).getType();
			}
		} catch (ClassNotFoundException e) {
			logger.error(InternationalizationManager.getString(Const.THERE_IS_NO_CLASS_WITH_THE_NAME) + "  " + sClassName, e);
			return null;
		} catch (NoSuchFieldException e) {
			logger.error(InternationalizationManager.getString(Const.THERE_IS_NO_FIELD_WITH_THE_NAME) + "  " + sFieldName, e);
		} catch (SecurityException e) {
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * creates new instance from class name
	 * @param sClassName
	 * @return
	 */
	private static Object getClassIntance(String sClassName) {
		Object object = null;
		try {
			Class<?> cl = Class.forName(sClassName);
			if (cl != null) {
				try {
					object = cl.newInstance();
				} catch (InstantiationException e) {
					logger.error(InternationalizationManager.getString("COULD_NOT_INSTANCE_CLASS") + " "  + sClassName, e);
					return object;
				} catch (IllegalAccessException e) {
					logger.error(InternationalizationManager.getString("COULD_NOT_INSTANCE_CLASS")  + sClassName, e);
					return object;
				} 
			}
		} catch (ClassNotFoundException e) {
			logger.error(InternationalizationManager.getString("THERE_IS_NO_CLASS_WITH_THE_NAME") + sClassName, e);
			return object;
		} 
		return object;
	}
}