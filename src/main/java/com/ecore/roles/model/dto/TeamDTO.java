package com.ecore.roles.model.dto;

import com.ecore.roles.client.model.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class TeamDTO {

    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID teamLeadId;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<UUID> teamMemberIds;

    public static TeamDTO fromModel(Team team) {
        if (team == null) {
            return null;
        }
        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .teamLeadId(team.getTeamLeadId())
                .teamMemberIds(team.getTeamMemberIds())
                .build();
    }
}
