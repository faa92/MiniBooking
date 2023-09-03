package com.example.minibooking.repository;

import com.example.minibooking.model.tenant.Tenant;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TenantJpaRepository extends BaseJpaRepository<Tenant, Long> implements TenantRepository {
    public TenantJpaRepository() {
        super(Tenant.class);
    }

    @Override
    public Optional<Tenant> findByEmail(String email) {
        return entityManager.createQuery("""
                        SELECT tenant
                        FROM Tenant tenant
                        WHERE lower(tenant.email) = lower(:email) 
                        """, Tenant.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
}
