package org.example.lesson15;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientFilter {
    private String name;
    private String city;
    private ClientStatus status;
    private Integer minAge;
    private Integer maxAge;
    private LocalDate registeredAfter;
    private LocalDate registeredBefore;
}
