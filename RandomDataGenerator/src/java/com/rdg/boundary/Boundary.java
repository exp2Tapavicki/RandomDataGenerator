package com.rdg.boundary;

import com.rdg.constants.Const;

import java.io.Serializable;

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

public class Boundary implements Serializable {

    private static final long serialVersionUID = 7013227683678850932L;
    private Object objMin;
    private Object objMax;
    private Object objPrecision = Const.FIVE;
    private boolean bAllowNulls = true;
    private String[] objEnum;

    public Boundary(Object objMin, Object objMax, boolean bAllowNulls, Object objPrecision, String[] objEnum) {
        super();
        this.objMin = objMin;
        this.objMax = objMax;
        this.bAllowNulls = bAllowNulls;
        this.objPrecision = objPrecision;
        this.objEnum = objEnum;
    }

    public Boundary() {
        super();
        this.objMin = null;
        this.objMax = null;
        this.bAllowNulls = true;
        this.objPrecision = Const.FIVE;
        this.objEnum = null;
    }

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

    public boolean isbAllowNulls() {
        return bAllowNulls;
    }

    public void setbAllowNulls(boolean bAllowNulls) {
        this.bAllowNulls = bAllowNulls;
    }

    public String[] getObjEnum() {
        return objEnum;
    }

    public void setObjEnum(String[] objEnum) {
        this.objEnum = objEnum;
    }

}
