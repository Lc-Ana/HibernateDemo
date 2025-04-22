package org.endava.hibernatedemo.HibernateTests;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.endava.hibernatedemo.entities.User;
import org.endava.hibernatedemo.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class HibernateOtherTechniquesTests {

    private static Session session;
    private static Transaction transaction;

    @BeforeAll
    public static void setup() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    @AfterAll
    public static void teardown() {
        transaction.commit();
        session.close();
        HibernateUtil.shutdown();
    }

    //Not included in GenericDAO and not implemented in any EntityDAO
    @Test
    public void createQueryTest() {
        String hqlQuery = "FROM Person";
        Query<User> userQuery = session.createQuery(hqlQuery, User.class);
        List<User> allUsers = userQuery.getResultList();

        for (User user : allUsers) {
            session.remove(user);
        }

        List<User> remainingUsers = session.createQuery(hqlQuery, User.class).getResultList();
        Assertions.assertTrue(remainingUsers.isEmpty(), "All users should be deleted");
    }

    //Not included in GenericDAO and not implemented in any EntityDAO
    @Test
    public void nativeQueryTest() {
        String sqlQuery = "SELECT * FROM users ORDER BY user_firstname ASC";
        Query<User> query = session.createNativeQuery(sqlQuery, User.class);
        Optional<User> user = query.list().stream().findFirst();
        Assertions.assertEquals("Lila", user.get().getFirstName(), "Users are not ordered");
    }

    //Not included in GenericDAO and not implemented in any EntityDAO
    @Test
    public void findUsersWithCriteriaBuilder() {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        CriteriaQuery<User> userWhereNameLike = criteriaQuery.select(root).where(criteriaBuilder.like(root.get("name"), "M%"));
        Query<User> userQuery = session.createQuery(userWhereNameLike);
        List<User> allUsers = userQuery.getResultList();
        Assertions.assertEquals(1, allUsers.size());
    }
}
