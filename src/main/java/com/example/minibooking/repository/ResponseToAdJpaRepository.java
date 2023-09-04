package com.example.minibooking.repository;

import com.example.minibooking.model.responseToAd.ResponseToAd;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResponseToAdJpaRepository extends BaseJpaRepository<ResponseToAd, Long> implements ResponseToAdRepository {
    public ResponseToAdJpaRepository() {
        super(ResponseToAd.class);
    }

    @Override
    public List<ResponseToAd> findPageByTenant(long tenantId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT responsetoad
                        FROM ResponseToAd responseToAd
                        WHERE responseToAd.tenant.id = :tenantId
                        ORDER BY responseToAd.createdAt DESC
                        """, ResponseToAd.class)
                .setParameter("tenantId", tenantId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public List<ResponseToAd> findByRentalAdLandlordAndTenant(long landlordId, long tenantId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT responsetoad
                        FROM ResponseToAd responseToAd
                        WHERE responseToAd.rentalAd.landlord.id = :landlordId
                        AND responseToAd.tenant.id = :tenantId
                        ORDER BY responseToAd.createdAt DESC
                        """, ResponseToAd.class)
                .setParameter("landlordId", landlordId)
                .setParameter("tenantId", tenantId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public int countResponseToAdByLandlord(long landlordId) {
        Integer count = entityManager.createQuery("""
                        SELECT COUNT (responsetoad)
                        FROM ResponseToAd responseToAd
                        WHERE responseToAd.rentalAd.landlord.id = :landlordId
                        """, Integer.class)
                .setParameter("landlordId", landlordId)
                .getSingleResult();

        return count.intValue();
    }
}
