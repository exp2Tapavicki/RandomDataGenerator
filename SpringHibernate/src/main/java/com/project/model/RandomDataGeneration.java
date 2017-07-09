package com.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "random_data_generation")
public class RandomDataGeneration implements Serializable {

    private static final long serialVersionUID = -2186063879911465403L;
    @Id
    private Integer id;
    @Id
    @Column(name = "ordinal_number", nullable = false)
    private Integer ordinalNumber;
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
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id", nullable = false, insertable=false, updatable=false)
    private RandomDataGenerationModel randomDataGenerationModel;

    public RandomDataGenerationModel getRandomDataGenerationModel() {
        return randomDataGenerationModel;
    }

    public void setRandomDataGenerationModel(RandomDataGenerationModel randomDataGenerationModel) {
        this.randomDataGenerationModel = randomDataGenerationModel;
    }

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

    public Integer getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(Integer ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }
}
