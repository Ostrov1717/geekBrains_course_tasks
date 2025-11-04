package org.example.lesson11.connections.one_to_one;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class User {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "profile_id") // FK в таблице users
    private UserProfile profile;
}
