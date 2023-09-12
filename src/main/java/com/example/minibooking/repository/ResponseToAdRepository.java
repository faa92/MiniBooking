package com.example.minibooking.repository;

import com.example.minibooking.model.responseToAd.ResponseToAd;

import java.util.List;
import java.util.Optional;

public interface ResponseToAdRepository extends BaseRepository<ResponseToAd, Long> {

    List<ResponseToAd> findPageByLandlordWithRentalAdAndTenant(long landlordId, int pageSize, int pageNumber);

    List<ResponseToAd> findPageByTenant(long tenantId, int pageSize, int pageNumber);

    List<ResponseToAd> findByRentalAdLandlordAndTenant(long landlordId, long tenantId, int pageSize, int pageNumber);

    int countResponseToAdByLandlord(long rentalAdId);

    Optional<ResponseToAd> findByRentalAdAndTenant(long rentalAdId, long tenantId);
}
