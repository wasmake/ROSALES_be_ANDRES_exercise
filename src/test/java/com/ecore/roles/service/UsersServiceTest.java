package com.ecore.roles.service;

import com.ecore.roles.client.UsersClient;
import com.ecore.roles.client.model.User;
import com.ecore.roles.service.impl.UsersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.ecore.roles.utils.TestData.GIANNI_USER;
import static com.ecore.roles.utils.TestData.UUID_1;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @InjectMocks
    private UsersServiceImpl usersService;
    @Mock
    private UsersClient usersClient;

    @Test
    void shouldGetUserWhenUserIdExists() {
        User gianniUser = GIANNI_USER();
        when(usersClient.getUser(UUID_1))
                .thenReturn(ResponseEntity
                        .status(HttpStatus.OK)
                        .body(gianniUser));

        assertNotNull(usersService.getUser(UUID_1));
    }
}
