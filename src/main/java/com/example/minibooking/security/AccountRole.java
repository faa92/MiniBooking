package com.example.minibooking.security;

import org.springframework.security.core.GrantedAuthority;

public enum AccountRole implements GrantedAuthority {
    LANDLORD,
    TENANT;
    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
