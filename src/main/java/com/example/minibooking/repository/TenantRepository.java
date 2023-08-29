package com.example.minibooking.repository;

import com.example.minibooking.model.tenant.Tenant;

import java.util.Optional;

public interface TenantRepository extends BaseRepository<Tenant, Long> {

    Optional<Tenant> findByEmail(String email);
}
