package com.project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "random_data_generation_model")
public class RandomDataGenerationModel implements Serializable {
    
    private static final long serialVersionUID = 5198839732240549997L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotEmpty
    @Column(name = "model_name", nullable = false)
    private String modelName;
    
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "app_user_id", nullable = false, insertable=true, updatable=true)
    private User user;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy="randomDataGenerationModel")
    private List<RandomDataGeneration> randomDataGenerations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<RandomDataGeneration> getRandomDataGenerations() {
        return randomDataGenerations;
    }

    public void setRandomDataGenerations(List<RandomDataGeneration> randomDataGenerations) {
        this.randomDataGenerations = randomDataGenerations;
    }
    
    public String getModelNameSelect(){
        return this.modelName + " - "+ this.getUser().getSsoId();
    }
    
    public void setModelNameSelect(String modelNameSelect){
        this.modelName = modelNameSelect.substring(0, modelNameSelect.indexOf(" - ") -2);
    }

}
