package com.rdg.range.filter;

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
