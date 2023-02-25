package com.ecore.roles.controller.operation;

import com.ecore.roles.model.dto.MembershipDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface MembershipsOperations {

    ResponseEntity<MembershipDTO> assignRoleToMembership(
            MembershipDTO membership);

    ResponseEntity<List<MembershipDTO>> getMemberships(
            UUID roleId);

}
