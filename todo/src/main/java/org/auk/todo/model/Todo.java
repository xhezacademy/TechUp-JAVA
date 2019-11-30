package org.auk.todo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String note;
    Date dueDate;
    int priority;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="FK_user_id"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "label_id", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_label_id"))
    Label label;

}
