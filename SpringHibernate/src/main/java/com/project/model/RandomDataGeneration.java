package com.project.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "random_data_generation")
public class RandomDataGeneration implements Serializable {

    private static final long serialVersionUID = -2186063879911465403L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Column(name = "basic_class_constants", nullable = false)
    private String basicClassConstants;
    @NotEmpty
    @Column(name = "property_name", nullable = false)
    private String propertyName;
    @Column(name = "obj_min", nullable = false)
    private String objMin;
    @Column(name = "obj_max", nullable = false)
    private String objMax;
    @Column(name = "obj_precision", nullable = false)
    private String objPrecision;
    @Column(name = "b_allow_nulls", nullable = false)
    private boolean bAllowNulls = true;
    @Column(name = "obj_enum", nullable = false)
    private String objEnum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBasicClassConstants() {
        return basicClassConstants;
    }

    public void setBasicClassConstants(String basicClassConstants) {
        this.basicClassConstants = basicClassConstants;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getObjMin() {
        return objMin;
    }

    public void setObjMin(String objMin) {
        this.objMin = objMin;
    }

    public String getObjMax() {
        return objMax;
    }

    public void setObjMax(String objMax) {
        this.objMax = objMax;
    }

    public String getObjPrecision() {
        return objPrecision;
    }

    public void setObjPrecision(String objPrecision) {
        this.objPrecision = objPrecision;
    }

    public boolean isbAllowNulls() {
        return bAllowNulls;
    }

    public void setbAllowNulls(boolean bAllowNulls) {
        this.bAllowNulls = bAllowNulls;
    }

    public String getObjEnum() {
        return objEnum;
    }

    public void setObjEnum(String objEnum) {
        this.objEnum = objEnum;
    }
}
