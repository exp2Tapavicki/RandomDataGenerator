package com.afowd.range.filter;

public abstract class RangeFilter {
	protected String sNameOfTheClass = null;
	protected String sProperty = null;
	protected boolean bIsInRange = true;
	protected boolean bAllowNull = false;
	
	public String getNameOfTheClass() {
		return sNameOfTheClass;
	}
	
	public void setNameOfTheClass(String sNameOfTheClass) {
		this.sNameOfTheClass = sNameOfTheClass;
	}
	
	public String getProperty() {
		return sProperty;
	}
	
	public void setProperty(String sProperty) {
		this.sProperty = sProperty;
	}
	
	public boolean getIsInRange() {
		return bIsInRange;
	}

	public void setAllowNull(boolean bAllowNull) {
		this.bAllowNull = bAllowNull;
	}
	
	public boolean getAllowNull() {
		return bAllowNull;
	}

	public void setIsInRange(boolean bIsInRange) {
		this.bIsInRange = bIsInRange;
	}
	
	abstract Object getRangeFrom();
	abstract Object getRangeTo();
}
