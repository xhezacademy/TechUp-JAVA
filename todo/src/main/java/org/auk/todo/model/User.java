package org.auk.todo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String firstName;
    String lastName;
    @Column(unique = true)
    String email;
    String username;
    String password;
    @Temporal(TemporalType.DATE)
    Date joinedAt;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
    private List<Todo> todoList;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_role_id"))
    Role role;

    @PrePersist
    protected void onCreate() {
        joinedAt = new Date();
    }
}
