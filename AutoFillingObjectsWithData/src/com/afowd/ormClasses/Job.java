package com.afowd.ormClasses;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Job {
	private BigInteger id = null;
	private String name = null;
	private HashMap<Worker, Contacts> hm = null;
	private ArrayList<Worker> colWorker = null;
	private Worker worker = null;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<Worker, Contacts> getHm() {
		return hm;
	}

	public void setHm(HashMap<Worker, Contacts> hm) {
		this.hm = hm;
	}

	public ArrayList<Worker> getColWorker() {
		return colWorker;
	}

	public void setColWorker(ArrayList<Worker> colWorker) {
		this.colWorker = colWorker;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}
}
