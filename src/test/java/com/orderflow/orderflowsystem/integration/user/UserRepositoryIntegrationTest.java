package com.orderflow.orderflowsystem.integration.user;

import com.orderflow.orderflowsystem.domain.user.Role;
import com.orderflow.orderflowsystem.domain.user.User;
import com.orderflow.orderflowsystem.domain.user.UserRepository;
import com.orderflow.orderflowsystem.integration.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @Test
    void shouldSaveUser() {

        User user = new User(
                null,
                "Gleison",
                "gleison@email.com",
                "123456",
                Role.CUSTOMER
        );

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
    }

    @Test
    void shouldNotAllowDuplicateEmail() {

        User user1 = new User(
                null,
                "Gleison",
                "gleison@email.com",
                "123456",
                Role.CUSTOMER
        );

        User user2 = new User(
                null,
                "Outro",
                "gleison@email.com",
                "654321",
                Role.ADMIN
        );

        userRepository.save(user1);

        assertThrows(
                Exception.class,
                () -> userRepository.saveAndFlush(user2)
        );
    }

    @Test
    void shouldReturnEmptyWhenUserDoesNotExist() {

        Optional<User> result =
                userRepository.findByEmail(
                        "naoexiste@email.com"
                );

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindUserByEmail() {

        User user = new User(
                null,
                "Gleison",
                "gleison2@email.com",
                "123456",
                Role.CUSTOMER
        );

        userRepository.save(user);

        Optional<User> result =
                userRepository.findByEmail(
                        "gleison2@email.com"
                );

        assertTrue(result.isPresent());

        assertEquals(
                "Gleison",
                result.get().getName()
        );
    }




}
