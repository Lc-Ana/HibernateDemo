package org.endava.hibernatedemo.HibernateTests;

import org.endava.hibernatedemo.dao.UserDAO;
import org.endava.hibernatedemo.entities.User;
import org.endava.hibernatedemo.utils.HibernateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HibernateDAOTests {

    private static UserDAO userDAO;

    @BeforeAll
    public static void setup() {
        userDAO = new UserDAO(HibernateUtil.getSessionFactory(), User.class);
    }

    @Test
    public void createUser() {
        //Create object to persist
        User user = new User("Kate", "Bakery", 24, "kate@endava.com");

        //Persist it
        userDAO.save(user); //create example
        User loaded = userDAO.findById(user.getId()); //basic read example

        // Assert
        assertNotNull(loaded);
        assertEquals("Kate", loaded.getFirstName());
    }

    @Test
    public void updateUser() {
        User userToUpdate = userDAO.findById(8L);
        userToUpdate.setEmail("kate.bakery@endava.com");
        userDAO.update(userToUpdate);

        User updatedUser = userDAO.findById(8L);
        Assertions.assertEquals("kate.bakery@endava.com", updatedUser.getEmail());
    }

    @Test
    public void deleteUser() {
        User userToDelete = userDAO.findById(8L);
        userDAO.delete(userToDelete);

        User updatedUser = userDAO.findById(8L);
        Assertions.assertNull(updatedUser);
    }
}
