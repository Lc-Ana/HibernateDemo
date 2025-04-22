package org.endava.hibernatedemo.dao;

import jakarta.persistence.EntityManager;
import org.endava.hibernatedemo.entities.Person;

import java.io.Serializable;
import java.util.List;

public class GenericDAOImpl_JPA<T> implements GenericDAO<T> {
    private final Class<T> entityClass;
    private final EntityManager entityManager;


    public GenericDAOImpl_JPA(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public T findById(Serializable id) {
        return entityManager.find(entityClass, id);
    }

    public Person findByName(String name) {
        String hql = "FROM Person WHERE name = :name";
        return entityManager.createQuery(hql, Person.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
    }
}
