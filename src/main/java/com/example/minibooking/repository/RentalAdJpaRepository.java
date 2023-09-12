package com.example.minibooking.repository;

import com.example.minibooking.model.rentalAd.RentalAd;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RentalAdJpaRepository extends BaseJpaRepository<RentalAd, Long> implements RentalAdRepository {
    public RentalAdJpaRepository() {
        super(RentalAd.class);
    }


    @Override
    public List<RentalAd> findAllAdsByTitle(String title, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT rentalAd
                        FROM RentalAd rentalAd
                        WHERE rentalAd.title =:title
                        AND rentalAd.active = true 
                        GROUP BY rentalAd.id
                        ORDER BY rentalAd.createdAt DESC 
                        """, RentalAd.class)
                .setParameter("title", title)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public List<RentalAd> findPageOfActiveListingsLandlordByTitle(String title, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT rentalAd
                        FROM RentalAd rentalAd
                        JOIN FETCH rentalAd.landlord
                        WHERE rentalAd.active
                        AND rentalAd.title ILIKE :title
                        ORDER BY rentalAd.createdAt DESC
                        """, RentalAd.class)
                .setParameter("title", title)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public List<RentalAd> findPageByLandlord(long landlordId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT rentalAd
                        FROM RentalAd rentalAd
                        WHERE rentalAd.landlord.id = :landlordId
                        ORDER BY rentalAd.createdAt DESC
                        """, RentalAd.class)
                .setParameter("landlordId", landlordId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public List<RentalAd> findPageActiveRentalAdByLowPrice(int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT rentalAd
                        FROM RentalAd rentalAd
                        WHERE rentalAd.active = true
                        ORDER BY rentalAd.price ASC
                        """, RentalAd.class)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public List<RentalAd> getAvailableRentalAdsInDateRange(LocalDate start, LocalDate end, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT rentalAd
                        FROM RentalAd rentalAd
                        WHERE rentalAd.active = true
                        AND rentalAd NOT IN (
                            SELECT responseToAd.rentalAd
                            FROM ResponseToAd responseToAd
                            WHERE responseToAd.dateTo >= :start
                            AND responseToAd.dateFrom <= :end)
                        """, RentalAd.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

}
