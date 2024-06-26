package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    //generate a test for the updateUser method that will test if the method returns true after a successful update
    @Test
    public void testUpdateUser_SuccessfulUpdate() {
        String username = "johndoe";
        User user = new User();
        user.setUsername(username);
        user.setFirstName("John");
        user.setLastName("Doe");

        User updatedInfo = new User();
        updatedInfo.setEmail("test@gmai.com");

        when(userDAO.findByUsername(username)).thenReturn(Optional.of(user));

        String isUpdated = userService.updateUser(username, updatedInfo);


        String expected = "User " + user.getFirstName() + " " + user.getLastName() + "'s profile was updated successfully!";
        assertEquals(expected, isUpdated);
    }


    //generate a test for the updateUser method that will test if the method throws a NoSuchElementException when the user does not exist
    @Test
    public void testUpdateUser_UserDoesNotExist(){
        String username = "johndoe";
        User updatedInfo = new User();
        when(userDAO.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            userService.updateUser(username, updatedInfo);
        });
    }
}
