package com.example.minibooking.security;

import lombok.Value;

import java.time.Instant;

@Value
public class AccessToken {
    String value;
    AccountPrincipal accountPrincipal;
    Instant issuedAt;
    Instant expiresAt;
}
