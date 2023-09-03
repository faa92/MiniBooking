package com.example.minibooking.model.tenant;

import lombok.Value;

@Value
public class TenantSignInDto {
    String email;
    String password;
}
