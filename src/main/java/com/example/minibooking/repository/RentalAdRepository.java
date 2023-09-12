package com.example.minibooking.repository;

import com.example.minibooking.model.rentalAd.RentalAd;

import java.time.LocalDate;
import java.util.List;

public interface RentalAdRepository extends BaseRepository<RentalAd, Long> {
    List<RentalAd> findPageByLandlord(long landlordId, int pageSize, int pageNumber);

    List<RentalAd> findPageActiveRentalAdByLowPrice(int pageSize, int pageNumber);

    List<RentalAd> getAvailableRentalAdsInDateRange(LocalDate start, LocalDate end, int pageSize, int pageNumber);

    List<RentalAd> findPageOfActiveListingsLandlordByTitle(String title, int pageSize, int pageNumber);


}
