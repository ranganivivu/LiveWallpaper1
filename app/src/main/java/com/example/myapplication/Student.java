package com.example.myapplication;

public class Student {
    public String fulname,username,email,gender;

    public Student(){

    }
    public Student(String fulname, String username, String email, String gender) {
        this.fulname = fulname;
        this.username = username;
        this.email = email;
        this.gender = gender;
    }


    public String getFulname() {
        return fulname;
    }

    public void setFulname(String fulname) {
        this.fulname = fulname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
