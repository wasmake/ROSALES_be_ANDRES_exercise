package com.ecore.roles.controller;

import com.ecore.roles.model.Role;
import com.ecore.roles.service.RolesService;
import com.ecore.roles.controller.operation.RolesOperations;
import com.ecore.roles.model.dto.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ecore.roles.model.dto.RoleDTO.fromModel;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/roles")
public class RolesController implements RolesOperations {

    private final RolesService rolesService;

    @Override
    @PostMapping(
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<RoleDTO> createRole(
            @Valid @RequestBody RoleDTO role) {
        return ResponseEntity
                .status(201)
                .body(fromModel(rolesService.createRole(role.toModel())));
    }

    @Override
    @GetMapping(
            produces = {"application/json"})
    public ResponseEntity<List<RoleDTO>> getRoles(
            @RequestParam(required = false) UUID teamId,
            @RequestParam(required = false) UUID teamMemberId
    ) {
        List<Role> roles = rolesService.getRoles(teamId, teamMemberId);
        List<RoleDTO> roleDTOList = new ArrayList<>();

        for (Role role : roles) {
            RoleDTO roleDto = fromModel(role);
            roleDTOList.add(roleDto);
        }

        return ResponseEntity
                .status(200)
                .body(roleDTOList);
    }

    @Override
    @GetMapping(
            path = "/{roleId}",
            produces = {"application/json"})
    public ResponseEntity<RoleDTO> getRole(
            @PathVariable UUID roleId) {
        return ResponseEntity
                .status(200)
                .body(fromModel(rolesService.getRole(roleId)));
    }

    @Override
    @GetMapping(
            path = "/search",
            produces = {"application/json"})
    public ResponseEntity<RoleDTO> getRole(
            @RequestParam UUID teamId,
            @RequestParam UUID teamMemberId) {
        return ResponseEntity
                .status(200)
                .body(fromModel(rolesService.getRole(teamId, teamMemberId)));
    }

}