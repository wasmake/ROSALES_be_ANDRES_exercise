package com.ecore.roles.controller.operation;

import com.ecore.roles.model.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UsersOperations {

    ResponseEntity<List<UserDTO>> getUsers();

    ResponseEntity<UserDTO> getUser(UUID userId);
}
