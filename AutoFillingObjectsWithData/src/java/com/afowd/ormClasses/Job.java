package com.afowd.ormClasses;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Job.class)
public class Job implements Serializable {

    private static final long serialVersionUID = -551828305022702655L;
    private BigInteger id;
	private String name;
	private HashMap<String, Contacts> hm;
	private ArrayList<Worker> colWorker;
	private Worker worker;

    public Job() {
    }
    public Job(BigInteger id, String name, HashMap<String, Contacts> hm, ArrayList<Worker> colWorker, Worker worker) {
        this.id = id;
        this.name = name;
        this.hm = hm;
        this.colWorker = colWorker;
        this.worker = worker;
    }

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

    public HashMap<String, Contacts> getHm() {
        return hm;
    }

    public void setHm(HashMap<String, Contacts> hm) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;
        Job job = (Job) o;
        return Objects.equal(id, job.id) &&
                Objects.equal(name, job.name) &&
                Objects.equal(hm, job.hm) &&
                Objects.equal(colWorker, job.colWorker) &&
                Objects.equal(worker, job.worker);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Job{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
