package org.auk.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phone;
    @Transient
    private @Gender int gender;
    private Date birthday;

    public Student() {
    }

    public Student(int id, String firstName, String lastName, String email, @Gender int gender, Date dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthday = dob;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date dob) {
        this.birthday = dob;
    }

    @Override
    public String toString() {
        return Student.class + "\n [ID]: " + id + " [Name]: " + firstName;
    }
}
