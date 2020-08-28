package com.example.kmitbuysell;

public class User {
    public String name, roll, email,uid;

    public User() {

    }

    public User(String name, String roll, String email,String uid) {
        this.name = name;
        this.roll = roll;
        this.email = email;
        this.uid=uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
