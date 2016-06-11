package com.rdg.sort;

import com.rdg.constants.Const;
import com.rdg.util.RegularExpUtil;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

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

public class ClassPropertyComparator implements Comparator {

    private final static Logger logger = Logger.getLogger(ClassPropertyComparator.class);
    private Object[] classToCompare = null;
    private Object[] propertyToCompare = null;
    private int[] order = null;

    /**
     * @param classToCompare    class for compare
     * @param propertyToCompare property to compare
     */
    public ClassPropertyComparator(String classToCompare, String propertyToCompare) {
        this.classToCompare = new String[]{classToCompare};
        this.propertyToCompare = new String[]{propertyToCompare};
        order = new int[]{Const.SORT_ASC};
    }


    /**
     * compares with more then one value
     *
     * @param classToCompare    array of classes
     * @param propertyToCompare array of properties
     * @param orders            sort order (Const.SORT_DESC i Const.SORT_ASC)
     */
    public ClassPropertyComparator(String[] classToCompare, String[] propertyToCompare, int[] orders) {
        this.classToCompare = classToCompare;
        this.propertyToCompare = propertyToCompare;
        this.order = orders;
    }

    /**
     * invokes method of the comparable class example
     * classToCompare = Class1.Class2 propertyToCompare = prop1
     * Class1.getClass2().getProp1();
     *
     * @param obj             obj
     * @param sNameOfTheClass sNameOfTheClass
     * @param sNameOfProperty sNameOfProperty
     * @return obj
     */
    public static Object getValue(Object obj, String sNameOfTheClass, String sNameOfProperty) {
        try {
            if (obj != null) {
                String s[] = sNameOfTheClass.split(RegularExpUtil.MATCH_DOT);
                Method m;
                for (int i = Const.ONE; i < s.length; i++) {
                    m = obj.getClass().getMethod(Const.STRING_GET + s[i].substring(Const.ZERO, Const.ONE).toUpperCase() + s[i].substring(Const.ONE));
                    obj = m.invoke(obj, (Object[]) null);
                }
                m = obj.getClass().getMethod(Const.STRING_GET + sNameOfProperty.substring(Const.ZERO, Const.ONE).toUpperCase() + sNameOfProperty.substring(Const.ONE));
                obj = m.invoke(obj, (Object[]) null);
            }
        } catch (SecurityException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            obj = null;
            logger.debug(e);
        }
        return obj;
    }

    /**
     * compares two object
     */
    public int compare(Object obj1, Object obj2) {
        int retValue = 0;
        boolean ok = false;
        int i = 0;
        if (classToCompare != null) {
            while (!ok && i < classToCompare.length) {
                retValue = compare0(obj1, obj2, (String) classToCompare[i], (String) propertyToCompare[i], order[i]);
                if (retValue != 0)
                    ok = true;
                i++;
            }
        }
        return retValue;
    }

    /**
     * compares two object by properties
     *
     * @param obj1              obj1
     * @param obj2              obj2
     * @param classToCompare    classToCompare
     * @param propertyToCompare propertyToCompare
     * @param order             order
     * @return retValue
     */
    private int compare0(Object obj1, Object obj2, String classToCompare, String propertyToCompare, int order) {
        int retValue = Const.COMPARE_LESSER;
        Object oProp1 = getValue(obj1, classToCompare, propertyToCompare);
        Object oProp2 = getValue(obj2, classToCompare, propertyToCompare);
//		if oProp1 value is null it should return that is lesser
        if (oProp1 == null) {
            if (order == Const.SORT_DESC) {
                return retValue * Const.COMPARE_LESSER;
            } else {
                return retValue;
            }
        }
//		if oProp2 value is null it should return that is greater
        if (oProp2 == null) {
            if (order == Const.SORT_DESC) {
                return retValue;
            } else {
                return retValue * Const.COMPARE_LESSER;
            }
        }
        if ((oProp1 instanceof Comparable) &&
                (oProp2 instanceof Comparable)) {
            retValue = ((Comparable) oProp1).compareTo(oProp2);
            if (order == Const.SORT_DESC)
                retValue *= Const.MINUS_ONE;
        }
        return retValue;
    }
}

