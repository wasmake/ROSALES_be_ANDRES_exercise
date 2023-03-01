package com.ecore.roles.service.impl;

import com.ecore.roles.client.model.Team;
import com.ecore.roles.client.model.User;
import com.ecore.roles.exception.ResourceExistsException;
import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Membership;
import com.ecore.roles.model.Role;
import com.ecore.roles.repository.MembershipRepository;
import com.ecore.roles.repository.RoleRepository;
import com.ecore.roles.service.RolesService;
import com.ecore.roles.service.TeamsService;
import com.ecore.roles.service.UsersService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
public class RolesServiceImpl implements RolesService {

    public static final String DEFAULT_ROLE = "Developer";

    private final RoleRepository roleRepository;
    private final MembershipRepository membershipRepository;
    private final UsersService usersService;
    private final TeamsService teamsService;

    @Autowired
    public RolesServiceImpl(
            RoleRepository roleRepository,
            MembershipRepository membershipRepository,
            UsersService usersService,
            TeamsService teamsService) {
        this.roleRepository = roleRepository;
        this.membershipRepository = membershipRepository;
        this.usersService = usersService;
        this.teamsService = teamsService;
    }

    @Override
    public Role createRole(@NonNull Role r) {
        if (roleRepository.findByName(r.getName()).isPresent()) {
            throw new ResourceExistsException(Role.class);
        }
        return roleRepository.save(r);
    }

    @Override
    public Role getRole(@NonNull UUID rid) {
        return roleRepository.findById(rid)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class, rid));
    }

    @Override
    public Role getRole(@NonNull UUID teamId, @NonNull UUID memberId) {
        if (teamsService.getTeam(teamId) == null) {
            throw new ResourceNotFoundException(Team.class, teamId);
        }
        if (usersService.getUser(memberId) == null) {
            throw new ResourceNotFoundException(User.class, memberId);
        }
        return membershipRepository.findByUserIdAndTeamId(memberId, teamId)
                .orElseThrow(() -> new ResourceNotFoundException(Membership.class))
                .getRole();
    }

    @Override
    public List<Role> getRoles(UUID teamId, UUID memberId) {
        if (teamId == null && memberId == null) {
            return roleRepository.findAll();
        }

        ExampleMatcher matcher = ExampleMatcher.matching();

        if (teamId != null) {
            matcher = matcher.withMatcher("teamId", ExampleMatcher.GenericPropertyMatchers.exact());
        }

        if (memberId != null) {
            matcher = matcher.withMatcher("userId", ExampleMatcher.GenericPropertyMatchers.exact());
        }

        Example<Membership> example = Example.of(
                Membership.builder().teamId(teamId).userId(memberId).build(),
                matcher
        );
        List<Membership> memberships = membershipRepository.findAll(example);
        List<Role> uniqueRoles = new ArrayList<>();
        Set<UUID> roleIds = new HashSet<>();

        for (Membership membership : memberships) {
            Role role = membership.getRole();

            if (roleIds.add(role.getId())) {
                // if the roleIds set did not contain the role id,
                // add the role to the uniqueRoles list
                uniqueRoles.add(role);
            }
        }

        return uniqueRoles;
    }

    private Role getDefaultRole() {
        return roleRepository.findByName(DEFAULT_ROLE)
                .orElseThrow(() -> new IllegalStateException("Default role is not configured"));
    }
}
