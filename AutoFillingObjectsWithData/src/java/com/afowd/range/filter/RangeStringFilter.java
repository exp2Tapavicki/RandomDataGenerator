package com.afowd.range.filter;


public class RangeStringFilter extends RangeFilter<String>{
    public RangeStringFilter() {
    }
    public RangeStringFilter(String nameOfTheClass, String property, Boolean isInRange, Boolean allowNull, String rangeFrom, String rangeTo) {
        super(nameOfTheClass, property, isInRange, allowNull, rangeFrom, rangeTo);
    }
}
