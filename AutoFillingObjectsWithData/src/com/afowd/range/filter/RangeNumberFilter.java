package com.afowd.range.filter;

public class RangeNumberFilter extends RangeFilter{
	private Long rangeFrom = null;
	private Long rangeTo = null;
	
	public RangeNumberFilter(Long rangeFrom, Long rangeTo, boolean bIsInRange, String sNameOfTheClass, String sProperty){
		this.rangeFrom = rangeFrom;
		this.rangeTo = rangeTo;
		this.bIsInRange = bIsInRange;
		this.sNameOfTheClass = sNameOfTheClass;
		this.sProperty = sProperty;
	}
	
	public RangeNumberFilter(Long rangeFrom, Long rangeTo, boolean bIsInRange, String sNameOfTheClass, String sProperty, boolean bAllowNull){
		this.rangeFrom = rangeFrom;
		this.rangeTo = rangeTo;
		this.bIsInRange = bIsInRange;
		this.sNameOfTheClass = sNameOfTheClass;
		this.sProperty = sProperty;
		this.bAllowNull = bAllowNull;
	}
	
	public RangeNumberFilter() {

	}
	public Long getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(Long rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public Long getRangeTo() {
		return rangeTo;
	}
	public void setRangeTo(Long rangeTo) {
		this.rangeTo = rangeTo;
	}

	public boolean getIsInRange() {
		return bIsInRange;
	}

	public void setIsInRange(boolean bIsInRange) {
		this.bIsInRange = bIsInRange;
	}
}
