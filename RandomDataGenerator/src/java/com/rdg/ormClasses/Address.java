package com.rdg.ormClasses;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Address.class)
public class Address implements Serializable {

    private static final long serialVersionUID = 5000464942107728646L;
    private BigInteger id;
    private Worker worker;
    private String city;
    private String address;
    private List<String> list;
    private AnotherAddress anotherAddress;
    private short streetNumber;
    private byte streetNumber2;

    public Address() {
    }

    public Address(BigInteger id, Worker worker, String city, String address, List<String> list, AnotherAddress anotherAddress, short streetNumber, byte streetNumber2) {
        this.id = id;
        this.worker = worker;
        this.city = city;
        this.address = address;
        this.list = list;
        this.anotherAddress = anotherAddress;
        this.streetNumber = streetNumber;
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

    public AnotherAddress getAnotherAddress() {
        return anotherAddress;
    }

    public void setAnotherAddress(AnotherAddress anotherAddress) {
        this.anotherAddress = anotherAddress;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address1 = (Address) o;
        return streetNumber == address1.streetNumber &&
                streetNumber2 == address1.streetNumber2 &&
                Objects.equal(id, address1.id) &&
                Objects.equal(worker, address1.worker) &&
                Objects.equal(city, address1.city) &&
                Objects.equal(address, address1.address) &&
                Objects.equal(list, address1.list) &&
                Objects.equal(anotherAddress, address1.anotherAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, city, address, list, anotherAddress, streetNumber, streetNumber2);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Adress{");
        sb.append("id=").append(id);
        sb.append(", city='").append(city).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", list=").append(list);
        sb.append(", anotherAddress=").append(anotherAddress);
        sb.append(", streetNumber=").append(streetNumber);
        sb.append(", streetNumber2=").append(streetNumber2);
        sb.append('}');
        return sb.toString();
    }
}
