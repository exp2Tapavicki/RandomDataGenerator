package com.afowd.range.filter;


public class RangeStringFilter extends RangeFilter{
	private String sRangeFrom = null;
	private String sRangeTo = null;
	
	public void RangeFilterInteger(String sRangeFrom, String sRangeTo, boolean bIsInRange, String sNameOfTheClass, String sProperty){
		this.sRangeFrom = sRangeFrom;
		this.sRangeTo = sRangeTo;
		this.bIsInRange = bIsInRange;
		this.sNameOfTheClass = sNameOfTheClass;
		this.sProperty = sProperty;
	}
	public String getRangeFrom() {
		return sRangeFrom;
	}

	public void setRangeFrom(String sRangeFrom) {
		this.sRangeFrom = sRangeFrom;
	}

	public String getRangeTo() {
		return sRangeTo;
	}
	public void setRangeTo(String sRangeTo) {
		this.sRangeTo = sRangeTo;
	}

	public boolean getIsInRange() {
		return bIsInRange;
	}

	public void setIsInRange(boolean bIsInRange) {
		this.bIsInRange = bIsInRange;
	}
}
