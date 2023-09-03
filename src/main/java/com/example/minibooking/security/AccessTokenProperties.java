package com.example.minibooking.security;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
@ConfigurationProperties(prefix = "rental.access-token")
@Value
public class AccessTokenProperties {
    String secret;
    Duration timeToLive;
}
