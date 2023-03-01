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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    private Role getDefaultRole() {
        return roleRepository.findByName(DEFAULT_ROLE)
                .orElseThrow(() -> new IllegalStateException("Default role is not configured"));
    }
}
