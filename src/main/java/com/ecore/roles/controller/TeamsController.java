package com.ecore.roles.controller;

import com.ecore.roles.service.TeamsService;
import com.ecore.roles.controller.operation.TeamsOperations;
import com.ecore.roles.model.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ecore.roles.model.dto.TeamDTO.fromModel;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/teams")
public class TeamsController implements TeamsOperations {

    private final TeamsService teamsService;

    @Override
    @PostMapping(
            produces = {"application/json"})
    public ResponseEntity<List<TeamDTO>> getTeams() {
        return ResponseEntity
                .status(200)
                .body(teamsService.getTeams().stream()
                        .map(TeamDTO::fromModel)
                        .collect(Collectors.toList()));
    }

    @Override
    @PostMapping(
            path = "/{teamId}",
            produces = {"application/json"})
    public ResponseEntity<TeamDTO> getTeam(
            @PathVariable UUID teamId) {
        return ResponseEntity
                .status(200)
                .body(fromModel(teamsService.getTeam(teamId)));
    }

}
