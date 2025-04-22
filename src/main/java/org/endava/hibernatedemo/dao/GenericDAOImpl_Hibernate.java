package org.endava.hibernatedemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class GenericDAOImpl_Hibernate<T> implements GenericDAO<T> {
    private final Class<T> entityClass;
    protected SessionFactory sessionFactory;

    public GenericDAOImpl_Hibernate(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.remove(entity);
        tx.commit();
        session.close();
    }

    @Override
    public T findById(Serializable id) {
        Session session = sessionFactory.openSession();
        T entity = session.get(entityClass, id);
        session.close();
        return entity;
    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.openSession();
        List<T> list = session.createQuery("FROM " + entityClass.getName(), entityClass).list();
        session.close();
        return list;
    }
}
