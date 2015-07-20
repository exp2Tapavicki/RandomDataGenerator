package com.afowd.ormClasses;

import java.math.BigInteger;
import java.util.List;

public class AnotherAdress {
	private BigInteger id = null;
	private Worker worker = null;
	private String city = null;
	private String adress = null;
	private List<String> list = null;
	private AnotherAdress2 anotherAdress2 = null;
	
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
	public AnotherAdress2 getAnotherAdress2() {
		return anotherAdress2;
	}
	public void setAnotherAdress2(AnotherAdress2 anotherAdress2) {
		this.anotherAdress2 = anotherAdress2;
	}
}
