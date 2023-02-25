package com.ecore.roles.controller;

import com.ecore.roles.service.UsersService;
import com.ecore.roles.controller.operation.UsersOperations;
import com.ecore.roles.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ecore.roles.model.dto.UserDTO.fromModel;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/users")
public class UsersController implements UsersOperations {

    private final UsersService usersService;

    @Override
    @PostMapping(
            produces = {"application/json"})
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity
                .status(200)
                .body(usersService.getUsers().stream()
                        .map(UserDTO::fromModel)
                        .collect(Collectors.toList()));
    }

    @Override
    @PostMapping(
            path = "/{userId}",
            produces = {"application/json"})
    public ResponseEntity<UserDTO> getUser(
            @PathVariable UUID userId) {
        return ResponseEntity
                .status(200)
                .body(fromModel(usersService.getUser(userId)));
    }
}
