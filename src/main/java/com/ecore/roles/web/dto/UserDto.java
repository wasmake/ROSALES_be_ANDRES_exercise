package com.ecore.roles.web.dto;

import com.ecore.roles.client.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserDto {

    @JsonProperty
    private UUID id;
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @JsonProperty
    private String displayName;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String avatarUrl;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String location;

    public static UserDto fromModel(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .displayName(user.getDisplayName())
                .avatarUrl(user.getAvatarUrl())
                .location(user.getLocation())
                .build();
    }
}
