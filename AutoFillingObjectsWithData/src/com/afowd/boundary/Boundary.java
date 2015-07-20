package com.afowd.boundary;

import com.afowd.constants.Const;

public class Boundary {
	private Object objMin = null;
	private Object objMax = null;
	private Object objPrecision = Const.FIVE;
	public Boundary(Object objMin, Object objMax, boolean bAllowNullsPom, Object objPrecision) {
		super();
		this.objMin = objMin;
		this.objMax = objMax;
		this.bAllowNullsPom = bAllowNullsPom;
		this.objPrecision = objPrecision;
	}
	public Boundary() {
		super();
		this.objMin = null;
		this.objMax = null;
		this.bAllowNullsPom = true;
		this.objPrecision = Const.FIVE;
	}
	private boolean bAllowNullsPom = true;
	public Object getObjPrecision() {
		return objPrecision;
	}
	public void setObjPrecision(Object precision) {
		this.objPrecision = precision;
	}
	public Object getObjMin() {
		return objMin;
	}
	public void setObjMin(Object objMin) {
		this.objMin = objMin;
	}
	public Object getObjMax() {
		return objMax;
	}
	public void setObjMax(Object objMax) {
		this.objMax = objMax;
	}
	public boolean isbAllowNullsPom() {
		return bAllowNullsPom;
	}
	public void setbAllowNullsPom(boolean bAllowNullsPom) {
		this.bAllowNullsPom = bAllowNullsPom;
	}

}
