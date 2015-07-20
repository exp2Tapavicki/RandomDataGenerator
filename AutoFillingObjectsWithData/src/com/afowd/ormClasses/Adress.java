package com.afowd.ormClasses;

import java.math.BigInteger;
import java.util.List;

public class Adress {
	private BigInteger id = null;
	private Worker worker = null;
	private String city = null;
	private String adress = null;
	private List<String> list = null;
	private AnotherAdress anotherAdress = null;
	private short streetNumber = 0;
	private byte streetNumber2 = 0;
	
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
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public AnotherAdress getAnotherAdress() {
		return anotherAdress;
	}
	public void setAnotherAdress(AnotherAdress anotherAdress) {
		this.anotherAdress = anotherAdress;
	}
}
