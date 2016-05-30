package com.afowd.range.filter;

public abstract class RangeFilter<T> {
	protected String nameOfTheClass = null;
	protected String property = null;
	protected boolean isInRange = true;
	protected boolean allowNull = false;
    protected T rangeFrom = null;
    protected T rangeTo = null;

    public RangeFilter() {
    }

    public RangeFilter(String nameOfTheClass, String property, Boolean isInRange, Boolean allowNull, T rangeFrom, T rangeTo) {
        this.nameOfTheClass = nameOfTheClass;
        this.property = property;
        this.isInRange = isInRange;
        this.allowNull = allowNull;
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }

    public String getNameOfTheClass() {
        return nameOfTheClass;
    }

    public void setNameOfTheClass(String nameOfTheClass) {
        this.nameOfTheClass = nameOfTheClass;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean isInRange() {
        return isInRange;
    }

    public void setInRange(boolean inRange) {
        isInRange = inRange;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public T getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(T rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public T getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(T rangeTo) {
        this.rangeTo = rangeTo;
    }
}
