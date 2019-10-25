package org.auk.models;

import javax.persistence.*;
import java.util.Date;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
