package com.rdg.ormClasses;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AnotherAdress2.class)
public class AnotherAdress2 implements Serializable {


    private static final long serialVersionUID = 1940555154842485609L;
    private BigInteger id;
    private Worker worker;
    private String city;
    private String adress;
    private List<String> list;

    public AnotherAdress2() {
    }

    public AnotherAdress2(BigInteger id, Worker worker, String city, String adress, List<String> list) {
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
        if (!(o instanceof AnotherAdress2)) return false;
        AnotherAdress2 that = (AnotherAdress2) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(worker, that.worker) &&
                Objects.equal(city, that.city) &&
                Objects.equal(adress, that.adress) &&
                Objects.equal(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, worker, city, adress, list);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AnotherAdress2{");
        sb.append("id=").append(id);
        sb.append(", worker=").append(worker);
        sb.append(", city='").append(city).append('\'');
        sb.append(", adress='").append(adress).append('\'');
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
