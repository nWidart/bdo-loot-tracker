package com.bdoloottracker.securitystarter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties("security")
@PropertySource(value = {
    "classpath:security.properties",
    "classpath:security-${spring.profiles.active}.properties"
}, ignoreResourceNotFound = true)
public class SecurityProperties {

  @Value("${security.secret-key:secret}")
  private String secretKey = "secret";
}
