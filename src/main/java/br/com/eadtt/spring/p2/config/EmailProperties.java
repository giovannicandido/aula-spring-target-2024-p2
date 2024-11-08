package br.com.eadtt.spring.p2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "oficina.email-settings")
@Component
@Getter
@Setter
public class EmailProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private Boolean ssl;
    private String from;
}
