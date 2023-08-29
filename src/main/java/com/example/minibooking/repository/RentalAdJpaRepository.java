package com.example.minibooking.repository;

import com.example.minibooking.model.rentalAd.RentalAd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalAdJpaRepository extends BaseJpaRepository<RentalAd, Long> implements RentalAdRepository {
    public RentalAdJpaRepository() {
        super(RentalAd.class);
    }

    @Override
    public List<RentalAd> findPageByLandlord(long landlordId, int pageSize, int pageNumber) {
        return entityManager.createQuery("""
                        SELECT rentalad
                        FROM RentalAd rentalad
                        WHERE rentalad.landlord.id = :landlordId
                        ORDER BY rentalad.createdAt DESC 
                        """, RentalAd.class)
                .setParameter("landlordId", landlordId)
                .setMaxResults(pageSize)
                .setFirstResult(pageSize * pageNumber)
                .getResultList();
    }

    @Override
    public List<Optional<RentalAd>> findPageByActiveListingOfLandlord(long landlordId, int pageSize, int pageNumber) {
        List<RentalAd> rentalAds = entityManager.createQuery("""
                        SELECT rentalad
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
    public List<RentalAd> findAllActiveRentalAdByLowPrice(int pageSize, int pageNumber) {
        return null;
    }

    @Override
    public List<RentalAd> getAllActiveListingsOnThoseDates(LocalDate start, LocalDate end) {
        return null;
    }
}