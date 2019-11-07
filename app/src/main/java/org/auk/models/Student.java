package org.auk.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.scaffold.FieldLocator;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Transient
    private String phone;
    @Transient
    private @Gender int gender;
    @Transient
    private Date birthday;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public @interface Gender {
        int MALE = 1;
        int FEMALE = 2;
    }

    @Override
    public String toString() {
        return Student.class + "\n [ID]: " + id + " [Name]: " + firstName;
    }
}