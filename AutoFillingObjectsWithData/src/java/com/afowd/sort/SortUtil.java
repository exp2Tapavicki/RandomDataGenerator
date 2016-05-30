package com.afowd.sort;

import java.util.ArrayList;
import java.util.Collections;


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
     * sorting data with Collestion.sort() method
     *
     * @param al
     * @return al
     */
    @SuppressWarnings("unchecked")
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
