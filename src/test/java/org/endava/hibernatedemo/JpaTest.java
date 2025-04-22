package org.endava.hibernatedemo;

import jakarta.persistence.EntityManager;
import org.endava.hibernatedemo.dao.PersonDAO;
import org.endava.hibernatedemo.entities.Person;
import org.endava.hibernatedemo.utils.JpaUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JpaTest {
    private static EntityManager entityManager;
    private static PersonDAO personDAO;

    @BeforeAll
    public static void setup() {
        entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        personDAO = new PersonDAO(entityManager, Person.class);
    }

    @AfterAll
    public static void tearDown() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void insertPersonTest() {
        Person person = new Person("Adda");
        personDAO.save(person);
        assertNotNull(personDAO.findById(1L));
    }

    @Test
    public void findPersonTest() {
        Person personToFind = personDAO.findByName("Adda");

        assertNotNull(personToFind);
        assertEquals("Adda", personToFind.getName());
    }

    @Test
    public void deletePersonTest() {
        Person personToDelete = personDAO.findById(2L);
        assertNotNull(personToDelete);
        personDAO.delete(personToDelete);
        Person personToFind = personDAO.findById(2L);
        assertNull(personToFind);
    }
}
