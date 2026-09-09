package com.miet.complaintportal;

import com.miet.complaintportal.entity.User;
import com.miet.complaintportal.repository.UserRepository;
import com.miet.complaintportal.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setPassword("plainPassword");
        testUser.setRole("CUSTOMER");
    }

    @Test
    public void testCreateUser_EncryptsPassword() {
        when(passwordEncoder.encode("plainPassword")).thenReturn("encryptedPassword");
        when(userRepository.save(testUser)).thenReturn(testUser);

        User savedUser = userService.createUser(testUser);

        assertEquals("encryptedPassword", savedUser.getPassword());
    }

    @Test
    public void testLoginUser_Success() {
        testUser.setPassword("encryptedPassword");
        when(userRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(passwordEncoder.matches("plainPassword", "encryptedPassword")).thenReturn(true);

        User result = userService.loginUser("test@example.com", "plainPassword");

        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    public void testLoginUser_WrongPassword() {
        testUser.setPassword("encryptedPassword");
        when(userRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(passwordEncoder.matches("wrongPassword", "encryptedPassword")).thenReturn(false);

        User result = userService.loginUser("test@example.com", "wrongPassword");

        assertNull(result);
    }
}