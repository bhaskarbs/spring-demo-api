package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("Bhaskar");
        user.setEmail("bhaskar@gmail.com");

        when(userRepository.findAll()).thenReturn(List.of(user));

        // Act
        List<User> users = userService.getAllUsers();

        // Assert
        assertEquals(1, users.size());
        assertEquals("Bhaskar", users.get(0).getName());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testSaveUser() {
        // Arrange
        User user = new User();
        user.setName("Rahul");
        user.setEmail("rahul@gmail.com");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Rahul");
        savedUser.setEmail("rahul@gmail.com");

        when(userRepository.save(user)).thenReturn(savedUser);

        // Act
        User result = userService.saveUser(user);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Rahul", result.getName());
        verify(userRepository, times(1)).save(user);
    }
}