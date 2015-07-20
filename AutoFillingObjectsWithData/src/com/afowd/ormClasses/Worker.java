package com.afowd.ormClasses;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Worker {
	private BigInteger id = null;
	private Integer yearsOfExpirenceTotal = null;
	private int age = 0;
	private String firstName = null;
	private String lastName = null;
	private boolean isWorkingNow = false;
	private Boolean gender = false;
	private double solary = 0;
	private Double solary1 = null; 
	private float hourPrice = 0;
	private Float hourPrice1 = null;
	private short streetNumber = 0;
	private Short streetNumber1 = 0;
	private long height = 0;
	private Long height1 = null;
	private BigInteger workingHours = null;
	private BigDecimal workingHours1 = null;
	private byte streetNumber2 = 0;
	private char middleName = (char)0;
	private Character middleName1 = (char)0;
	private Date birthDate = null;
	private ArrayList<Job> jobAl = null;
	private ArrayList<Adress> adressAl = null;
	private Adress adress = null;
	private HashMap<AnotherAdress, Adress> hmAdresses = null;
	private HashMap<String, Adress> hmAdress = null;
	private HashMap<AnotherAdress, String> hmAnotherAdress = null;
	private HashMap<String, Integer> hmBasic = null;
	
	private Job currentJob = null;
	private Contacts contacts = null;
	
	public Short getStreetNumber1() {
		return streetNumber1;
	}
	public void setStreetNumber1(Short streetNumber1) {
		this.streetNumber1 = streetNumber1;
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
	public Double getSolary1() {
		return solary1;
	}
	public void setSolary1(Double solary1) {
		this.solary1 = solary1;
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
	public byte getStreetNumber2() {
		return streetNumber2;
	}
	public void setStreetNumber2(byte streetNumber2) {
		this.streetNumber2 = streetNumber2;
	}
	public Integer getYearsOfExpirenceTotal() {
		return yearsOfExpirenceTotal;
	}
	public void setYearsOfExpirenceTotal(Integer iYearsOfExpirenceTotal) {
		this.yearsOfExpirenceTotal = iYearsOfExpirenceTotal;
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
	public void setWorkingNow(boolean isWorkingNow) {
		this.isWorkingNow = isWorkingNow;
	}
	public double getSolary() {
		return solary;
	}
	public void setSolary(double solary) {
		this.solary = solary;
	}
	public float getHourPrice() {
		return hourPrice;
	}
	public void setHourPrice(float hourPrice) {
		this.hourPrice = hourPrice;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public char getMiddleName() {
		return middleName;
	}
	public void setMiddleName(char middleName) {
		this.middleName = middleName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
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
	public HashMap<AnotherAdress, Adress> getHmAdresses() {
		return hmAdresses;
	}
	public void setHmAdresses(HashMap<AnotherAdress, Adress> hmAdresses) {
		this.hmAdresses = hmAdresses;
	}
	public HashMap<String, Adress> getHmAdress() {
		return hmAdress;
	}
	public void setHmAdress(HashMap<String, Adress> hmAdress) {
		this.hmAdress = hmAdress;
	}
	public HashMap<AnotherAdress, String> getHmAnotherAdress() {
		return hmAnotherAdress;
	}
	public void setHmAnotherAdress(HashMap<AnotherAdress, String> hmAnotherAdress) {
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
	public Character getMiddleName1() {
		return middleName1;
	}
	public void setMiddleName1(Character middleName1) {
		this.middleName1 = middleName1;
	}
}
