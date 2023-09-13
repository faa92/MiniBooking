package com.example.minibooking.security;

import com.example.minibooking.model.tenant.Tenant;
import lombok.Value;

@Value

public class TenantPrincipal implements AccountPrincipal {

    long id;

    @Override
    public AccountRole getRole() {
        return AccountRole.TENANT;
    }

    public static TenantPrincipal from(Tenant tenant) {
        return new TenantPrincipal(tenant.getId());
    }
}
