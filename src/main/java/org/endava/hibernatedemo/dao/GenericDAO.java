package org.endava.hibernatedemo.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {
    void save(T entity);

    void update(T entity);

    void delete(T entity);

    T findById(Serializable id);

    List<T> findAll();
}
