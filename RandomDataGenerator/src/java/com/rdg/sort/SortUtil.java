package com.rdg.sort;

import java.util.ArrayList;
import java.util.Collections;

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

public class SortUtil {
    private String classToCompare = null;
    private String sortByProperty = null;

    public SortUtil() {
    }

    public SortUtil(String classToCompare, String sortByProperty) {
        this.classToCompare = classToCompare;
        this.sortByProperty = sortByProperty;
    }

    /**
     * sorting data with Collection.sort() method
     *
     * @param al
     * @return al
     */
    public ArrayList<?> sortBeanByPropertyASC(ArrayList<?> al) {
        if (al != null && !al.isEmpty()) {
            Collections.sort(al, new ClassPropertyComparator(classToCompare, sortByProperty));
        }
        return al;
    }

    public String getClassToCompare() {
        return classToCompare;
    }

    public void setClassToCompare(String classToCompare) {
        this.classToCompare = classToCompare;
    }

    public String getSortByProperty() {
        return sortByProperty;
    }

    public void setSortByProperty(String sortByProperty) {
        this.sortByProperty = sortByProperty;
    }
}
