package com.rdg.util;

import com.rdg.ormClasses.Worker;
import com.rdg.range.filter.RangeNumberFilter;

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

public class FilterUtil {

    /**
     * Filtering received data of workers with received filters. All values can be null except bInRangeTotal and bInRangeOrg. If any of this value for filtering is null it should not filter and add data if other filters
     * satisfy requirement. If filters are not null and value in worker for years of experience is null it should not filter that data and should add them too.
     *
     * @param alWorkers                  ArrayList of Workers with years of experience
     * @param iYearsOfExperienceTotalFrom filter for total years lower range filtering
     * @param iYearsOfExperienceTotalTo   filter for total years higher range filtering
     * @param bInRangeTotal              if true it is giving in range filter for iYearsOfExperienceTotalFrom and iYearsOfExperienceTotalTo variables else it is telling that they are out of that range
     * @param iAgeFrom                   filter for age from
     * @param iAgeTo                     filter for age to
     * @param bInRangeOrg                if true it is giving in range filter for iAgeFrom and iAgeTo else it is telling that they are out of that range
     * @return alWorkersTemp                filtered ArrayList of workers
     */
    public static ArrayList<Worker> filterDataByYearsOfExperienceAndAge(ArrayList<Worker> alWorkers, Integer iYearsOfExperienceTotalFrom, Integer iYearsOfExperienceTotalTo, boolean bInRangeTotal, Integer iAgeFrom, Integer iAgeTo, boolean bInRangeOrg) {
        ArrayList<Worker> alWorkersTemp = new ArrayList();
        if (alWorkers != null && !alWorkers.isEmpty()) {
            for (Worker worker : alWorkers) {
                Integer iYearsOfExperienceTotal = worker.getYearsOfExperienceTotal();
                Integer iAge = worker.getAge();
                if ((iYearsOfExperienceTotalFrom == null || (iYearsOfExperienceTotal == null || (bInRangeTotal ? iYearsOfExperienceTotal.intValue() >= iYearsOfExperienceTotalFrom.intValue() : iYearsOfExperienceTotal.intValue() <= iYearsOfExperienceTotalFrom.intValue())))) {
                    if (iYearsOfExperienceTotalTo == null || (iYearsOfExperienceTotal == null || (bInRangeTotal ? iYearsOfExperienceTotal.intValue() <= iYearsOfExperienceTotalTo.intValue() : iYearsOfExperienceTotal.intValue() >= iYearsOfExperienceTotalTo.intValue()))) {
                        if (iAgeFrom == null || (iAge == null || (bInRangeOrg ? iAge.intValue() >= iAgeFrom.intValue() : iAge.intValue() <= iAgeFrom.intValue()))) {
                            if (iAgeTo == null || (iAge == null || (bInRangeOrg ? iAge.intValue() <= iAgeTo.intValue() : iAge.intValue() >= iAgeTo.intValue()))) {
                                alWorkersTemp.add(worker);
                            }
                        }
                    }
                }
            }
        }
        return alWorkersTemp;
    }

    /**
     * filter array list with one range filter
     *
     * @param alData
     * @param rangeFilter
     * @return ArrayList<Object>
     */
    public static ArrayList<Object> filterRangeDataNumbers(ArrayList<Object> alData, RangeNumberFilter rangeFilter) {
        return RangeFilterUtil.filterRangeDataNumbers(alData, rangeFilter);
    }

    /**
     * filter array list with multiple range filters
     *
     * @param alData
     * @param alRangeFilter
     * @return ArrayList<Object>
     */
    public static ArrayList<Object> filterRangeDataNumbers(ArrayList<Object> alData, ArrayList<RangeNumberFilter> alRangeFilter) {
        return RangeFilterUtil.filterRangeDataNumbers(alData, alRangeFilter);
    }
}
