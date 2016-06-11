package com.rdg.ormClasses;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.common.base.Objects;
import com.rdg.enumeration.Gender;
import com.rdg.enumeration.Gender1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Worker.class)
public class Worker implements Serializable {

    private static final long serialVersionUID = -6785275562696986506L;
    private BigInteger id;
    private Integer yearsOfExpirenceTotal;
    private int age;
    private String firstName;
    private String lastName;
    private boolean isWorkingNow;
    private Gender gender;
    private Gender1 gender1;
    private double solary;
    private Double solary1;
    private float hourPrice;
    private Float hourPrice1;
    private short streetNumber;
    private Short streetNumber1;
    private long height;
    private Long height1;
    private BigInteger workingHours;
    private BigDecimal workingHours1;
    private byte streetNumber2;
    private char middleName;
    private Character middleName1;
    private Date birthDate;
    private ArrayList<Job> jobAl;
    private ArrayList<Adress> adressAl;
    private Adress adress;
    private HashMap<String, Adress> hmAdresses;
    private HashMap<String, Adress> hmAdress;
    private HashMap<String, String> hmAnotherAdress;
    private HashMap<String, Integer> hmBasic;
    private Job currentJob;
    private Contacts contacts;

    public Worker() {
    }

    public Worker(BigInteger id, Integer yearsOfExpirenceTotal, int age, String firstName, String lastName, boolean isWorkingNow, Gender gender, Gender1 gender1, double solary, Double solary1, float hourPrice, Float hourPrice1, short streetNumber, Short streetNumber1, long height, Long height1, BigInteger workingHours, BigDecimal workingHours1, byte streetNumber2, char middleName, Character middleName1, Date birthDate, ArrayList<Job> jobAl, ArrayList<Adress> adressAl, Adress adress, HashMap<String, Adress> hmAdresses, HashMap<String, Adress> hmAdress, HashMap<String, String> hmAnotherAdress, HashMap<String, Integer> hmBasic, Job currentJob, Contacts contacts) {
        this.id = id;
        this.yearsOfExpirenceTotal = yearsOfExpirenceTotal;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isWorkingNow = isWorkingNow;
        this.gender = gender;
        this.gender1 = gender1;
        this.solary = solary;
        this.solary1 = solary1;
        this.hourPrice = hourPrice;
        this.hourPrice1 = hourPrice1;
        this.streetNumber = streetNumber;
        this.streetNumber1 = streetNumber1;
        this.height = height;
        this.height1 = height1;
        this.workingHours = workingHours;
        this.workingHours1 = workingHours1;
        this.streetNumber2 = streetNumber2;
        this.middleName = middleName;
        this.middleName1 = middleName1;
        this.birthDate = birthDate;
        this.jobAl = jobAl;
        this.adressAl = adressAl;
        this.adress = adress;
        this.hmAdresses = hmAdresses;
        this.hmAdress = hmAdress;
        this.hmAnotherAdress = hmAnotherAdress;
        this.hmBasic = hmBasic;
        this.currentJob = currentJob;
        this.contacts = contacts;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getYearsOfExpirenceTotal() {
        return yearsOfExpirenceTotal;
    }

    public void setYearsOfExpirenceTotal(Integer yearsOfExpirenceTotal) {
        this.yearsOfExpirenceTotal = yearsOfExpirenceTotal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isWorkingNow() {
        return isWorkingNow;
    }

    public void setWorkingNow(boolean workingNow) {
        isWorkingNow = workingNow;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender1 getGender1() {
        return gender1;
    }

    public void setGender1(Gender1 gender1) {
        this.gender1 = gender1;
    }

    public double getSolary() {
        return solary;
    }

    public void setSolary(double solary) {
        this.solary = solary;
    }

    public Double getSolary1() {
        return solary1;
    }

    public void setSolary1(Double solary1) {
        this.solary1 = solary1;
    }

    public float getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(float hourPrice) {
        this.hourPrice = hourPrice;
    }

    public Float getHourPrice1() {
        return hourPrice1;
    }

    public void setHourPrice1(Float hourPrice1) {
        this.hourPrice1 = hourPrice1;
    }

    public short getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(short streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Short getStreetNumber1() {
        return streetNumber1;
    }

    public void setStreetNumber1(Short streetNumber1) {
        this.streetNumber1 = streetNumber1;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public Long getHeight1() {
        return height1;
    }

    public void setHeight1(Long height1) {
        this.height1 = height1;
    }

    public BigInteger getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(BigInteger workingHours) {
        this.workingHours = workingHours;
    }

    public BigDecimal getWorkingHours1() {
        return workingHours1;
    }

    public void setWorkingHours1(BigDecimal workingHours1) {
        this.workingHours1 = workingHours1;
    }

    public byte getStreetNumber2() {
        return streetNumber2;
    }

    public void setStreetNumber2(byte streetNumber2) {
        this.streetNumber2 = streetNumber2;
    }

    public char getMiddleName() {
        return middleName;
    }

    public void setMiddleName(char middleName) {
        this.middleName = middleName;
    }

    public Character getMiddleName1() {
        return middleName1;
    }

    public void setMiddleName1(Character middleName1) {
        this.middleName1 = middleName1;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<Job> getJobAl() {
        return jobAl;
    }

    public void setJobAl(ArrayList<Job> jobAl) {
        this.jobAl = jobAl;
    }

    public ArrayList<Adress> getAdressAl() {
        return adressAl;
    }

    public void setAdressAl(ArrayList<Adress> adressAl) {
        this.adressAl = adressAl;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public HashMap<String, Adress> getHmAdresses() {
        return hmAdresses;
    }

    public void setHmAdresses(HashMap<String, Adress> hmAdresses) {
        this.hmAdresses = hmAdresses;
    }

    public HashMap<String, Adress> getHmAdress() {
        return hmAdress;
    }

    public void setHmAdress(HashMap<String, Adress> hmAdress) {
        this.hmAdress = hmAdress;
    }

    public HashMap<String, String> getHmAnotherAdress() {
        return hmAnotherAdress;
    }

    public void setHmAnotherAdress(HashMap<String, String> hmAnotherAdress) {
        this.hmAnotherAdress = hmAnotherAdress;
    }

    public HashMap<String, Integer> getHmBasic() {
        return hmBasic;
    }

    public void setHmBasic(HashMap<String, Integer> hmBasic) {
        this.hmBasic = hmBasic;
    }

    public Job getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return age == worker.age &&
                isWorkingNow == worker.isWorkingNow &&
                Double.compare(worker.solary, solary) == 0 &&
                Float.compare(worker.hourPrice, hourPrice) == 0 &&
                streetNumber == worker.streetNumber &&
                height == worker.height &&
                streetNumber2 == worker.streetNumber2 &&
                middleName == worker.middleName &&
                Objects.equal(id, worker.id) &&
                Objects.equal(yearsOfExpirenceTotal, worker.yearsOfExpirenceTotal) &&
                Objects.equal(firstName, worker.firstName) &&
                Objects.equal(lastName, worker.lastName) &&
                gender == worker.gender &&
                gender1 == worker.gender1 &&
                Objects.equal(solary1, worker.solary1) &&
                Objects.equal(hourPrice1, worker.hourPrice1) &&
                Objects.equal(streetNumber1, worker.streetNumber1) &&
                Objects.equal(height1, worker.height1) &&
                Objects.equal(workingHours, worker.workingHours) &&
                Objects.equal(workingHours1, worker.workingHours1) &&
                Objects.equal(middleName1, worker.middleName1) &&
                Objects.equal(birthDate, worker.birthDate) &&
                Objects.equal(jobAl, worker.jobAl) &&
                Objects.equal(adressAl, worker.adressAl) &&
                Objects.equal(adress, worker.adress) &&
                Objects.equal(hmAdresses, worker.hmAdresses) &&
                Objects.equal(hmAdress, worker.hmAdress) &&
                Objects.equal(hmAnotherAdress, worker.hmAnotherAdress) &&
                Objects.equal(hmBasic, worker.hmBasic) &&
                Objects.equal(currentJob, worker.currentJob) &&
                Objects.equal(contacts, worker.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, yearsOfExpirenceTotal, age, firstName, lastName, isWorkingNow, gender, gender1, solary, solary1, hourPrice, hourPrice1, streetNumber, streetNumber1, height, height1, workingHours, workingHours1, streetNumber2, middleName, middleName1, birthDate, jobAl, adressAl, adress, hmAdresses, hmAdress, hmAnotherAdress, hmBasic, currentJob, contacts);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Worker{");
        sb.append("id=").append(id);
        sb.append(", yearsOfExpirenceTotal=").append(yearsOfExpirenceTotal);
        sb.append(", age=").append(age);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", isWorkingNow=").append(isWorkingNow);
        sb.append(", gender=").append(gender);
        sb.append(", gender1=").append(gender1);
        sb.append(", solary=").append(solary);
        sb.append(", solary1=").append(solary1);
        sb.append(", hourPrice=").append(hourPrice);
        sb.append(", hourPrice1=").append(hourPrice1);
        sb.append(", streetNumber=").append(streetNumber);
        sb.append(", streetNumber1=").append(streetNumber1);
        sb.append(", height=").append(height);
        sb.append(", height1=").append(height1);
        sb.append(", workingHours=").append(workingHours);
        sb.append(", workingHours1=").append(workingHours1);
        sb.append(", streetNumber2=").append(streetNumber2);
        sb.append(", middleName=").append(middleName);
        sb.append(", middleName1=").append(middleName1);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", jobAl=").append(jobAl);
        sb.append(", adressAl=").append(adressAl);
        sb.append(", adress=").append(adress);
        sb.append(", hmAdresses=").append(hmAdresses);
        sb.append(", hmAdress=").append(hmAdress);
        sb.append(", hmAnotherAdress=").append(hmAnotherAdress);
        sb.append(", hmBasic=").append(hmBasic);
        sb.append(", currentJob=").append(currentJob);
        sb.append(", contacts=").append(contacts);
        sb.append('}');
        return sb.toString();
    }
}
