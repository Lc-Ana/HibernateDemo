package org.endava.hibernatedemo.dao;

import org.endava.hibernatedemo.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDAO extends GenericDAOImpl_Hibernate<User> {

    public UserDAO(SessionFactory sessionFactory, Class<User> entityClass) {
        super(sessionFactory, entityClass);
    }

    public User findByEmail(String email, Session session) {
        String hql = "FROM Person WHERE email = :email";
        return session.createQuery(hql, User.class)
                .setParameter("email", email)
                .uniqueResult();
    }
}
