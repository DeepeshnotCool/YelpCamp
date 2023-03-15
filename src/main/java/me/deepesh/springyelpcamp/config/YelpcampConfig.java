package me.deepesh.springyelpcamp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "yelpcamp.app")
public class YelpcampConfig {

  private String jwtSecret;

  private Long jwtExpirationMs;
}
