package org.example.lesson15;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;


public class ClientSpecifications {

    public static Specification<Client> nameLike(String name) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Client> cityEquals(String city) {
        return (root, query, cb) ->
                cb.equal(cb.lower(root.get("city")), city.toLowerCase());
    }

    public static Specification<Client> statusEquals(ClientStatus status) {
        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }

    public static Specification<Client> ageGreaterThanOrEqual(Integer minAge) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("age"), minAge);
    }

    public static Specification<Client> ageLessThanOrEqual(Integer maxAge) {
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("age"), maxAge);
    }

    public static Specification<Client> registeredAfter(LocalDate date) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("registrationDate"), date);
    }

    public static Specification<Client> registeredBefore(LocalDate date) {
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("registrationDate"), date);
    }
}
