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
    private Integer yearsOfExperienceTotal;
    private int age;
    private String firstName;
    private String lastName;
    private boolean isWorkingNow;
    private Gender gender;
    private Gender1 gender1;
    private double salary;
    private Double salary1;
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
    private ArrayList<Address> addressAl;
    private Address address;
    private HashMap<String, Address> hmAddresses;
    private HashMap<String, Address> hmAddress;
    private HashMap<String, String> hmAnotherAddress;
    private HashMap<String, Integer> hmBasic;
    private Job currentJob;
    private Contacts contacts;

    public Worker() {
    }

    public Worker(BigInteger id, Integer yearsOfExperienceTotal, int age, String firstName, String lastName, boolean isWorkingNow, Gender gender, Gender1 gender1, double salary, Double salary1, float hourPrice, Float hourPrice1, short streetNumber, Short streetNumber1, long height, Long height1, BigInteger workingHours, BigDecimal workingHours1, byte streetNumber2, char middleName, Character middleName1, Date birthDate, ArrayList<Job> jobAl, ArrayList<Address> addressAl, Address address, HashMap<String, Address> hmAddresses, HashMap<String, Address> hmAddress, HashMap<String, String> hmAnotherAddress, HashMap<String, Integer> hmBasic, Job currentJob, Contacts contacts) {
        this.id = id;
        this.yearsOfExperienceTotal = yearsOfExperienceTotal;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isWorkingNow = isWorkingNow;
        this.gender = gender;
        this.gender1 = gender1;
        this.salary1 = salary;
        this.salary1 = salary1;
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
        this.addressAl = addressAl;
        this.address = address;
        this.hmAddresses = hmAddresses;
        this.hmAddress = hmAddress;
        this.hmAnotherAddress = hmAnotherAddress;
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

    public Integer getYearsOfExperienceTotal() {
        return yearsOfExperienceTotal;
    }

    public void setYearsOfExperienceTotal(Integer yearsOfExperienceTotal) {
        this.yearsOfExperienceTotal = yearsOfExperienceTotal;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Double getSalary1() {
        return salary1;
    }

    public void setSalary1(Double salary1) {
        this.salary1 = salary1;
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

    public ArrayList<Address> getAddressAl() {
        return addressAl;
    }

    public void setAddressAl(ArrayList<Address> addressAl) {
        this.addressAl = addressAl;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public HashMap<String, Address> getHmAddresses() {
        return hmAddresses;
    }

    public void setHmAddresses(HashMap<String, Address> hmAddresses) {
        this.hmAddresses = hmAddresses;
    }

    public HashMap<String, Address> getHmAddress() {
        return hmAddress;
    }

    public void setHmAddress(HashMap<String, Address> hmAddress) {
        this.hmAddress = hmAddress;
    }

    public HashMap<String, String> getHmAnotherAddress() {
        return hmAnotherAddress;
    }

    public void setHmAnotherAddress(HashMap<String, String> hmAnotherAddress) {
        this.hmAnotherAddress = hmAnotherAddress;
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
                Double.compare(worker.salary, salary) == 0 &&
                Float.compare(worker.hourPrice, hourPrice) == 0 &&
                streetNumber == worker.streetNumber &&
                height == worker.height &&
                streetNumber2 == worker.streetNumber2 &&
                middleName == worker.middleName &&
                Objects.equal(id, worker.id) &&
                Objects.equal(yearsOfExperienceTotal, worker.yearsOfExperienceTotal) &&
                Objects.equal(firstName, worker.firstName) &&
                Objects.equal(lastName, worker.lastName) &&
                gender == worker.gender &&
                gender1 == worker.gender1 &&
                Objects.equal(salary1, worker.salary1) &&
                Objects.equal(hourPrice1, worker.hourPrice1) &&
                Objects.equal(streetNumber1, worker.streetNumber1) &&
                Objects.equal(height1, worker.height1) &&
                Objects.equal(workingHours, worker.workingHours) &&
                Objects.equal(workingHours1, worker.workingHours1) &&
                Objects.equal(middleName1, worker.middleName1) &&
                Objects.equal(birthDate, worker.birthDate) &&
                Objects.equal(jobAl, worker.jobAl) &&
                Objects.equal(addressAl, worker.addressAl) &&
                Objects.equal(address, worker.address) &&
                Objects.equal(hmAddresses, worker.hmAddresses) &&
                Objects.equal(hmAddress, worker.hmAddress) &&
                Objects.equal(hmAnotherAddress, worker.hmAnotherAddress) &&
                Objects.equal(hmBasic, worker.hmBasic) &&
                Objects.equal(currentJob, worker.currentJob) &&
                Objects.equal(contacts, worker.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, yearsOfExperienceTotal, age, firstName, lastName, isWorkingNow, gender, gender1, salary, salary1, hourPrice, hourPrice1, streetNumber, streetNumber1, height, height1, workingHours, workingHours1, streetNumber2, middleName, middleName1, birthDate, jobAl, addressAl, address, hmAddresses, hmAddress, hmAnotherAddress, hmBasic, currentJob, contacts);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Worker{");
        sb.append("id=").append(id);
        sb.append(", yearsOfExperienceTotal=").append(yearsOfExperienceTotal);
        sb.append(", age=").append(age);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", isWorkingNow=").append(isWorkingNow);
        sb.append(", gender=").append(gender);
        sb.append(", gender1=").append(gender1);
        sb.append(", salary=").append(salary);
        sb.append(", salary1=").append(salary1);
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
        sb.append(", addressAl=").append(addressAl);
        sb.append(", address=").append(address);
        sb.append(", hmAddresses=").append(hmAddresses);
        sb.append(", hmAddress=").append(hmAddress);
        sb.append(", hmAnotherAddress=").append(hmAnotherAddress);
        sb.append(", hmBasic=").append(hmBasic);
        sb.append(", currentJob=").append(currentJob);
        sb.append(", contacts=").append(contacts);
        sb.append('}');
        return sb.toString();
    }
}
