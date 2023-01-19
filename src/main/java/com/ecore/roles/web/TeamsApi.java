package com.ecore.roles.web;

import com.ecore.roles.web.dto.TeamDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TeamsApi {

    ResponseEntity<List<TeamDto>> getTeams();

    ResponseEntity<TeamDto> getTeam(UUID teamId);

}
