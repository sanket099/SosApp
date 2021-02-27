package com.sanket.safewe;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EmergencyContacts {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String email;

    private String phoneNumber;
    private String address;

   /* public EmergencyContacts() {
    }*/

    public EmergencyContacts(String name, String email, String phoneNumber, String address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
