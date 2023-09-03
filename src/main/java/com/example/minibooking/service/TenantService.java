package com.example.minibooking.service;

import com.example.minibooking.model.tenant.TenantSignInDto;
import com.example.minibooking.model.tenant.TenantSignUpDto;
import com.example.minibooking.security.AccessToken;

public interface TenantService {
    AccessToken signIn(TenantSignInDto dto);

    AccessToken signUp(TenantSignUpDto dto);
}
