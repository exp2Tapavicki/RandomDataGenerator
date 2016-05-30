package com.afowd.range.filter;

import java.util.Date;

public class RangeDateFilter extends RangeFilter<Date>{
    public RangeDateFilter() {
    }
    public RangeDateFilter(String nameOfTheClass, String property, Boolean isInRange, Boolean allowNull, Date rangeFrom, Date rangeTo) {
        super(nameOfTheClass, property, isInRange, allowNull, rangeFrom, rangeTo);
    }
}
