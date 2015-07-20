package com.afowd.range.filter;

import java.util.Date;

public class RangeDateFilter extends RangeFilter{
	private Date rangeFrom = null;
	private Date rangeTo = null;
	
	public void RangeFilterInteger(Date rangeFrom, Date rangeTo, boolean bIsInRange, String sNameOfTheClass, String sProperty){
		this.rangeFrom = rangeFrom;
		this.rangeTo = rangeTo;
		this.bIsInRange = bIsInRange;
		this.sNameOfTheClass = sNameOfTheClass;
		this.sProperty = sProperty;
	}
	public Date getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(Date rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public Date getRangeTo() {
		return rangeTo;
	}
	public void setRangeTo(Date rangeTo) {
		this.rangeTo = rangeTo;
	}

	public boolean getIsInRange() {
		return bIsInRange;
	}

	public void setIsInRange(boolean bIsInRange) {
		this.bIsInRange = bIsInRange;
	}
}
