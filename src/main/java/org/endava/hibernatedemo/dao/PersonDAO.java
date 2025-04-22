package org.endava.hibernatedemo.dao;

import jakarta.persistence.EntityManager;
import org.endava.hibernatedemo.entities.Person;

public class PersonDAO extends GenericDAOImpl_JPA<Person> {

    public PersonDAO(EntityManager entityManager, Class<Person> entityClass) {
        super(entityManager, entityClass);
    }
}
