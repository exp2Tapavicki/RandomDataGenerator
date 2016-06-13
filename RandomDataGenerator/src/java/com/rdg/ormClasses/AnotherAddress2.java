package com.rdg.ormClasses;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AnotherAddress2.class)
public class AnotherAddress2 implements Serializable {


    private static final long serialVersionUID = 1940555154842485609L;
    private BigInteger id;
    private Worker worker;
    private String city;
    private String address;
    private List<String> list;

    public AnotherAddress2() {
    }

    public AnotherAddress2(BigInteger id, Worker worker, String city, String address, List<String> list) {
        this.id = id;
        this.worker = worker;
        this.city = city;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        if (!(o instanceof AnotherAddress2)) return false;
        AnotherAddress2 that = (AnotherAddress2) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(worker, that.worker) &&
                Objects.equal(city, that.city) &&
                Objects.equal(address, that.address) &&
                Objects.equal(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, worker, city, address, list);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AnotherAddress2{");
        sb.append("id=").append(id);
        sb.append(", worker=").append(worker);
        sb.append(", city='").append(city).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
