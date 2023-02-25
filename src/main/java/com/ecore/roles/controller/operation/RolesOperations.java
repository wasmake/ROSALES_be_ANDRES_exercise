package com.ecore.roles.controller.operation;

import com.ecore.roles.model.dto.RoleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface RolesOperations {

    ResponseEntity<RoleDTO> createRole(
            RoleDTO role);

    ResponseEntity<List<RoleDTO>> getRoles();

    ResponseEntity<RoleDTO> getRole(
            UUID roleId);

    ResponseEntity<RoleDTO> getRole(
            UUID teamMemberId,
            UUID teamId);

}