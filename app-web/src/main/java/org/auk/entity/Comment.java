package org.auk.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_article_id"))
    Article article;
    String authorName;
    String authorEmail;
    String commendBody;
    Date createdAt;

}

