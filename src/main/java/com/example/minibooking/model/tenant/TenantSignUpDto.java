package com.example.minibooking.model.tenant;

import lombok.Value;

@Value
public class TenantSignUpDto {
    String name;
    String email;
    String password;
}
