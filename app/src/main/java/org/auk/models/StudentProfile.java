package org.auk.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "student_profile")
public class StudentProfile {

    @Id
    private int id;
    private String phoneNumber;
    private String alternateEmail;
    private Date birthday;
    @Column(columnDefinition = "text")
    private String biography;

    @OneToOne
    @MapsId
    private Student student;

}
