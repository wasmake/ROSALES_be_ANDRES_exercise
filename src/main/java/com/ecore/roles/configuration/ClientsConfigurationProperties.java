package com.ecore.roles.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "clients")
public class ClientsConfigurationProperties {

    private String usersApiHost;

    private String teamsApiHost;

}
