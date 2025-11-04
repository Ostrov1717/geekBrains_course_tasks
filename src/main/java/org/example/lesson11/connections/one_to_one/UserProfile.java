package org.example.lesson11.connections.one_to_one;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UserProfile {
    @Id
    private Long id;

    @OneToOne(mappedBy = "profile")
    private User user;

}
