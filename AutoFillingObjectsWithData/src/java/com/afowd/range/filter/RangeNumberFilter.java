package com.afowd.range.filter;

public class RangeNumberFilter extends RangeFilter<Long> {
    public RangeNumberFilter() {
    }
    public RangeNumberFilter(String nameOfTheClass, String property, Boolean isInRange, Boolean allowNull, Long rangeFrom, Long rangeTo) {
        super(nameOfTheClass, property, isInRange, allowNull, rangeFrom, rangeTo);
    }
}
