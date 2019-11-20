package org.auk.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    @Column(unique = true)
    String slug;
    String content;
    @OneToMany(mappedBy = "article")
    Set<Comment> comments;
    boolean published;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    Date cratedAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    Date updatedAt;

    @PrePersist
    protected void onCreate() {
        updatedAt = cratedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
