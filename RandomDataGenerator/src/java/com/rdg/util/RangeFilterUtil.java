package com.rdg.util;

import com.rdg.range.filter.RangeNumberFilter;
import com.rdg.sort.ClassPropertyComparator;

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

public class RangeFilterUtil {
    /**
     * checks if object value is in range filters
     *
     * @param alData
     * @param rangeFilter
     * @return
     */
    public static ArrayList<Object> filterRangeDataNumbers(ArrayList<Object> alData, RangeNumberFilter rangeFilter) {
        if (alData != null && !alData.isEmpty() && rangeFilter != null && rangeFilter.getClass() != null && rangeFilter.getProperty() != null) {
            ArrayList<Object> alFilteredData = new ArrayList<>();
            for (Object object : alData) {
                if (object != null) {
                    Object oValue = ClassPropertyComparator.getValue(object, rangeFilter.getNameOfTheClass(), rangeFilter.getProperty());
                    if (rangeFilter.isAllowNull() && oValue == null) {
                        alFilteredData.add(object);
                        continue;
                    }
                    if (oValue != null && (oValue instanceof Integer || oValue instanceof Long || oValue instanceof Short)) {
                        if (inRangeInteger(oValue, rangeFilter)) {
                            alFilteredData.add(object);
                        }
                    }
                }
            }
            return alFilteredData;
        } else {
            return alData;
        }
    }

    /**
     * checks if object value is in range filter
     *
     * @param oValue
     * @param rangeFilterNumber
     * @return
     */
    private static boolean inRangeInteger(Object oValue, RangeNumberFilter rangeFilterNumber) {
        Long lValue = new Long(oValue.toString());
        Long lFilterFrom = rangeFilterNumber.getRangeFrom();
        Long lFilterTo = rangeFilterNumber.getRangeTo();
        boolean bInRange = rangeFilterNumber.isInRange();
        boolean bAllowNull = rangeFilterNumber.isAllowNull();
        if ((lFilterFrom == null || (lValue != null ? (bInRange ? lValue.longValue() >= lFilterFrom.longValue() : lValue.longValue() <= lFilterFrom.longValue()) : bAllowNull))) {
            if ((lFilterTo == null || (lValue != null ? (bInRange ? lValue.longValue() <= lFilterTo.longValue() : lValue.longValue() >= lFilterTo.longValue()) : bAllowNull))) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if object value is in range filters
     *
     * @param alData
     * @param alRangeFilter
     * @return
     */
    public static ArrayList<Object> filterRangeDataNumbers(ArrayList<Object> alData, ArrayList<RangeNumberFilter> alRangeFilter) {
        if (alData != null && !alData.isEmpty() && alRangeFilter != null && !alRangeFilter.isEmpty()) {
            ArrayList<Object> alFilteredData = new ArrayList<>();
            for (Object object : alData) {
                boolean bIsInRange = true;
                for (RangeNumberFilter rangeNumberFilter : alRangeFilter) {
                    if (rangeNumberFilter.getClass() != null && rangeNumberFilter.getProperty() != null) {
                        Object oValue = ClassPropertyComparator.getValue(object, rangeNumberFilter.getNameOfTheClass(), rangeNumberFilter.getProperty());
                        if (!rangeNumberFilter.isAllowNull() && oValue == null) {
                            bIsInRange = false;
                            break;
                        }
                        if (oValue != null && (oValue instanceof Integer || oValue instanceof Long || oValue instanceof Short)) {
                            if (!inRangeInteger(oValue, rangeNumberFilter)) {
                                bIsInRange = false;
                                break;
                            }
                        }
                    }
                }
                if (bIsInRange) {
                    alFilteredData.add(object);
                }
            }
            return alFilteredData;
        } else {
            return alData;
        }
    }
}
