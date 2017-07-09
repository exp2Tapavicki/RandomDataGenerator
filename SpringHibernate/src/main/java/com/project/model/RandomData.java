package com.project.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public class RandomData implements Serializable {

    private static final long serialVersionUID = 3886127311336904154L;
    @NotNull
    private Long numberOfDataApplicant;
    @NotNull
    private Long numberOfDataVacancies;
    
    private boolean downloadFile;
    
    private String modelName;
    
    private List<RandomDataGenerationModel> randomDataGenerationModels;

    public Long getNumberOfDataApplicant() {
        return numberOfDataApplicant;
    }

    public void setNumberOfDataApplicant(Long numberOfDataApplicant) {
        this.numberOfDataApplicant = numberOfDataApplicant;
    }

    public Long getNumberOfDataVacancies() {
        return numberOfDataVacancies;
    }

    public void setNumberOfDataVacancies(Long numberOfDataVacancies) {
        this.numberOfDataVacancies = numberOfDataVacancies;
    }

    public boolean isDownloadFile() {
        return downloadFile;
    }

    public void setDownloadFile(boolean downloadFile) {
        this.downloadFile = downloadFile;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<RandomDataGenerationModel> getRandomDataGenerationModels() {
        return randomDataGenerationModels;
    }

    public void setRandomDataGenerationModels(List<RandomDataGenerationModel> randomDataGenerationModels) {
        this.randomDataGenerationModels = randomDataGenerationModels;
    }
}
