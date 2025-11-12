package org.example.lesson15;

import org.springframework.data.jpa.domain.Specification;

public class ClientSpecificationsBuilder {

    public static Specification<Client> build(ClientFilter filter) {
        Specification<Client> spec = Specification.where(null);

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
        }

        if (filter.getCity() != null && !filter.getCity().isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("city")), filter.getCity().toLowerCase()));
        }

        if (filter.getStatus() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("status"), filter.getStatus()));
        }

        if (filter.getMinAge() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("age"), filter.getMinAge()));
        }

        if (filter.getMaxAge() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("age"), filter.getMaxAge()));
        }

        if (filter.getRegisteredAfter() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("registrationDate"), filter.getRegisteredAfter()));
        }

        if (filter.getRegisteredBefore() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("registrationDate"), filter.getRegisteredBefore()));
        }

        return spec;
    }

}
