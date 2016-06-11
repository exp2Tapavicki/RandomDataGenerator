package com.rdg.ormClasses;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Adress.class)
public class Adress implements Serializable {

    private static final long serialVersionUID = 5000464942107728646L;
    private BigInteger id;
    private Worker worker;
    private String city;
    private String adress;
    private List<String> list;
    private AnotherAdress anotherAdress;
    private short streetNumber;
    private byte streetNumber2;

    public Adress() {
    }

    public Adress(BigInteger id, Worker worker, String city, String adress, List<String> list, AnotherAdress anotherAdress, short streetNumber, byte streetNumber2) {
        this.id = id;
        this.worker = worker;
        this.city = city;
        this.adress = adress;
        this.list = list;
        this.anotherAdress = anotherAdress;
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
        if (!(o instanceof Adress)) return false;
        Adress adress1 = (Adress) o;
        return streetNumber == adress1.streetNumber &&
                streetNumber2 == adress1.streetNumber2 &&
                Objects.equal(id, adress1.id) &&
                Objects.equal(worker, adress1.worker) &&
                Objects.equal(city, adress1.city) &&
                Objects.equal(adress, adress1.adress) &&
                Objects.equal(list, adress1.list) &&
                Objects.equal(anotherAdress, adress1.anotherAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, city, adress, list, anotherAdress, streetNumber, streetNumber2);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Adress{");
        sb.append("id=").append(id);
        sb.append(", city='").append(city).append('\'');
        sb.append(", adress='").append(adress).append('\'');
        sb.append(", list=").append(list);
        sb.append(", anotherAdress=").append(anotherAdress);
        sb.append(", streetNumber=").append(streetNumber);
        sb.append(", streetNumber2=").append(streetNumber2);
        sb.append('}');
        return sb.toString();
    }
}
