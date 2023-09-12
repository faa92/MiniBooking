package com.example.minibooking.repository;

import com.example.minibooking.model.responseToAd.ResponseToAd;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ResponseToAdJpaRepository extends BaseJpaRepository<ResponseToAd, Long> implements ResponseToAdRepository {
    public ResponseToAdJpaRepository() {
        super(ResponseToAd.class);
    }

    @Override
    public ResponseToAd confirmBooking(long responseAdId) {
        return entityManager.createQuery("""
                SELECT responseToAd
                FROM ResponseToAd responseToAd
                JOIN FETCH responseToAd.rentalAd
                JOIN FETCH responseToAd.tenant
                WHERE responseToAd.id = :responseToAdId
                                        
                """);
    }

    @Override
    public List<ResponseToAd> findPageByLandlordWithRentalAdAndTenant(long landlordId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT responseToAd
                        FROM ResponseToAd responseToAd
                        JOIN FETCH responseToAd.rentalAd
                        JOIN FETCH responseToAd.tenant
                        WHERE responseToAd.rentalAd.id = :landlordId
                        ORDER BY responseToAd.createdAt DESC                    
                        """, ResponseToAd.class)
                .setParameter("landlordId", landlordId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public List<ResponseToAd> findPageByTenant(long tenantId, int pageSize, int pageNumber) { //todo
        return entityManager.createQuery("""
                        SELECT responseToAd
                        FROM ResponseToAd responseToAd
                        WHERE responseToAd.tenant.id = :tenantId
                        ORDER BY responseToAd.createdAt DESC
                        """, ResponseToAd.class)
                .setParameter("tenantId", tenantId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override //todo
    public List<ResponseToAd> findByRentalAdLandlordAndTenant(long landlordId, long tenantId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT responseToAd
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
    public int countResponseToAdByLandlord(long landlordId) { //todo
        Integer count = entityManager.createQuery("""
                        SELECT COUNT (responseToAd)
                        FROM ResponseToAd responseToAd
                        WHERE responseToAd.rentalAd.landlord.id = :landlordId
                        """, Integer.class)
                .setParameter("landlordId", landlordId)
                .getSingleResult();

        return count.intValue();
    }

    @Override
    public Optional<ResponseToAd> findByRentalAdAndTenant(long rentalAdId, long tenantId) {//todo
        return entityManager.createQuery("""
                        SELECT responseToAd
                        FROM ResponseToAd responseToAd
                        WHERE responseToAd.rentalAd.id = :rentalAdId
                        AND responseToAd.tenant.id = :tenantId
                        """, ResponseToAd.class)
                .setParameter("rentalAdId", rentalAdId)
                .setParameter("tenantId", tenantId)
                .getResultStream()
                .findFirst();
    }
}
