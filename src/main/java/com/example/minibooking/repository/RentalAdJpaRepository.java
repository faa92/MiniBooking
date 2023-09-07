package com.example.minibooking.repository;

import com.example.minibooking.model.rentalAd.RentalAd;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RentalAdJpaRepository extends BaseJpaRepository<RentalAd, Long> implements RentalAdRepository {
    public RentalAdJpaRepository() {
        super(RentalAd.class);
    }

//    @Override
//    public List<RentalAd> findPageByLandlord(long landlordId, int pageSize, int pageNumber) {
//        return entityManager.createQuery("""
//                        SELECT rentalad
//                        FROM RentalAd rentalAd
//                        WHERE rentalAd.landlord.id = :landlordId
//                        ORDER BY rentalAd.createdAt DESC
//                        """, RentalAd.class)
//                .setParameter("landlordId", landlordId)
//                .setMaxResults(pageSize)
//                .setFirstResult(pageSize * pageNumber)
//                .getResultList();
//    }

    @Override
    public List<Optional<RentalAd>> findPageByActiveListingOfLandlord(long landlordId, int pageSize, int pageNumber) {
        List<RentalAd> rentalAds = entityManager.createQuery("""
                        SELECT rentalAd
                        FROM RentalAd rentalAd
                        WHERE rentalAd.landlord.id = : landlordId
                        AND rentalAd.active = true
                        ORDER BY rentalAd.createdAt DESC 
                        """, RentalAd.class)
                .setParameter("landlordId", landlordId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultStream()
                .toList();

        List<Optional<RentalAd>> result = new ArrayList<>();
        for (RentalAd rentalAd : rentalAds) {
            result.add(Optional.of(rentalAd));
        }
        return result;
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
                            SELECT ResponseToAd.rentalAd
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

}
