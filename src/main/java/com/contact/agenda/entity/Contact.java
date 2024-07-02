package com.contact.agenda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;


@Entity
public class Contact implements Serializable {


    @Id
    private UUID id;
    private String name;
    private String number;
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEMail() {
        return email;
    }

    public void setMail(String email) {
        this.email = email;
    }
}
