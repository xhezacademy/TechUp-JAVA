package org.auk.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue
    int id;
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    private Date startDate;
    private Date endDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp")
    private Date createdAt;

}
