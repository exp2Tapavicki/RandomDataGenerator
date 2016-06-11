package com.project.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vacancy")
public class Vacancy implements Serializable {

    private static final long serialVersionUID = -46961832091003841L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "vacancy_name", nullable = false)
    private String vacancyName;

    @NotEmpty
    @Column(name = "vacancy_code", nullable = false)
    private String vacancyCode;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "vacancy_applicant",
            joinColumns = {@JoinColumn(name = "vacancy_id")},
            inverseJoinColumns = {@JoinColumn(name = "applicant_id")})
    private Set<Applicant> applicants = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public String getVacancyCode() {
        return vacancyCode;
    }

    public void setVacancyCode(String vacancyCode) {
        this.vacancyCode = vacancyCode;
    }

    public Set<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(Set<Applicant> applicants) {
        this.applicants = applicants;
    }

    @Override
    public String toString() {
        return this.getVacancyName();
    }
}
