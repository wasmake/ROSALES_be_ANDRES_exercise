package com.ecore.roles.controller.operation;

import com.ecore.roles.model.dto.TeamDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TeamsOperations {

    ResponseEntity<List<TeamDTO>> getTeams();

    ResponseEntity<TeamDTO> getTeam(UUID teamId);

}
