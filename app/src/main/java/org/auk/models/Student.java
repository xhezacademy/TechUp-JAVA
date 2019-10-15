package org.auk.models;

import java.util.Date;

public class Student {

    public Student() {
    }

    public Student(int id, String firstName, String lastName, String email, @Gender int gender, Date dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

//    enum Gender {
//        MALE,
//        FEMALE
//    }

    public @interface Gender {
        int MALE = 1;
        int FEMALE = 2;
    }

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private @Gender int gender;
    private Date dob;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(@Gender int gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return Student.class + "\n [ID]: " + id + " [Name]: " + firstName;
    }
}
