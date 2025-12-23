package demo.backend;

import demo.backend.model.User;
import demo.backend.repository.UserRepo;
import demo.backend.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(UUID.randomUUID());
        testUser.setUsername("testuser");
        testUser.setPassword("password");
        testUser.setRole("USER");
    }

    @Test
    void signup_ShouldSaveAndReturnUser() {
        when(userRepo.save(any(User.class))).thenReturn(testUser);
        User result = userService.signup(testUser);
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepo, times(1)).save(testUser);
    }

    @Test
    void loadUserByUsername_ShouldThrowException_WhenNotFound() {
        when(userRepo.findByUsername("nonexistent")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, 
            () -> userService.loadUserByUsername("nonexistent"));
    }
}