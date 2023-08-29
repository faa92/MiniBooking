package com.example.minibooking.repository;

import com.example.minibooking.model.rentalAd.RentalAd;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalAdRepository extends BaseRepository<RentalAd, Long> {

    List<RentalAd> findPageByLandlord(long landlordId, int pageSize, int pageNumber);

    List<Optional<RentalAd>> findPageByActiveListingOfLandlord(long landlordId, int pageSize, int pageNumber);

    List<RentalAd> findAllActiveRentalAdByLowPrice(int pageSize, int pageNumber);

    List<RentalAd> getAllActiveListingsOnThoseDates(LocalDate start, LocalDate end);//todo
}
