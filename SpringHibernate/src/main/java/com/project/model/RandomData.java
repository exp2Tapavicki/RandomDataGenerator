package com.project.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RandomData implements Serializable {

    private static final long serialVersionUID = 3886127311336904154L;
    @NotNull
    private Long numberOfDataApplicant;
    @NotNull
    private Long numberOfDataVacancies;

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
}
