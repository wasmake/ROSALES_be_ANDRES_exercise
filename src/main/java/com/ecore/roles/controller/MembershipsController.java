package com.ecore.roles.controller;

import com.ecore.roles.model.Membership;
import com.ecore.roles.service.MembershipsService;
import com.ecore.roles.controller.operation.MembershipsOperations;
import com.ecore.roles.model.dto.MembershipDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ecore.roles.model.dto.MembershipDTO.fromModel;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/roles/memberships")
public class MembershipsController implements MembershipsOperations {

    private final MembershipsService membershipsService;

    @Override
    @PostMapping(
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<MembershipDTO> assignRoleToMembership(
            @NotNull @Valid @RequestBody MembershipDTO membershipDto) {
        Membership membership = membershipsService.assignRoleToMembership(membershipDto.toModel());
        return ResponseEntity
                .status(201)
                .body(fromModel(membership));
    }

    @Override
    @GetMapping(
            path = "/search",
            produces = {"application/json"})
    public ResponseEntity<List<MembershipDTO>> getMemberships(
            @RequestParam UUID roleId) {

        List<Membership> memberships = membershipsService.getMemberships(roleId);

        List<MembershipDTO> newMembershipDTO = new ArrayList<>();

        for (Membership membership : memberships) {
            MembershipDTO membershipDto = fromModel(membership);
            newMembershipDTO.add(membershipDto);
        }

        return ResponseEntity
                .status(200)
                .body(newMembershipDTO);
    }

}
