package org.example.lesson11.connections.one_to_one;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "profile_id") // FK в таблице users
    private UserProfile profile;
}
