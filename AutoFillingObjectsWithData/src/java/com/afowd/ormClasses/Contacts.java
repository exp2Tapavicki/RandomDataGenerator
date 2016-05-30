package com.afowd.ormClasses;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Contacts.class)
public class Contacts implements Serializable {

    private static final long serialVersionUID = 5307056577164497528L;
    private BigInteger id;
	private Worker worker;
	private String city;
	private String adress;
	private List<String> list;

    public Contacts() {
    }
    public Contacts(BigInteger id, Worker worker, String city, String adress, List<String> list) {
        this.id = id;
        this.worker = worker;
        this.city = city;
        this.adress = adress;
        this.list = list;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacts)) return false;
        Contacts contacts = (Contacts) o;
        return Objects.equal(id, contacts.id) &&
                Objects.equal(worker, contacts.worker) &&
                Objects.equal(city, contacts.city) &&
                Objects.equal(adress, contacts.adress) &&
                Objects.equal(list, contacts.list);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, city, adress, list);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contacts{");
        sb.append("id=").append(id);
        sb.append(", city='").append(city).append('\'');
        sb.append(", adress='").append(adress).append('\'');
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
