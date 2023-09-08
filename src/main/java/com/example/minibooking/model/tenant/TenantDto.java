package com.example.minibooking.model.tenant;

import lombok.Value;

@Value
public class TenantDto {
    String email;
    String name;

    public static TenantDto from(Tenant tenant) {
        return new TenantDto(
                tenant.getEmail(),
                tenant.getName()
        );
    }

}
