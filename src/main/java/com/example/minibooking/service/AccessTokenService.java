package com.example.minibooking.service;

import com.example.minibooking.security.AccessToken;
import com.example.minibooking.security.AccountPrincipal;

public interface AccessTokenService {
    AccessToken generate(AccountPrincipal accountPrincipal);

    AccountPrincipal authenticate(String accessTokenValue);
}
