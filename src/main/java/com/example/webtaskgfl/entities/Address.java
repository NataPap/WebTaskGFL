package com.example.webtaskgfl.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor

public class Address {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "street", length = 200, nullable = false)
    private String street;
    @Column(name = "suite", length = 200, nullable = false)
    private String suite;
    @Column(name = "city", length = 200, nullable = false)
    private String city;
    @Column(name = "zipcode", length = 200, nullable = false)
    private String zipcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
