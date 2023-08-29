package com.example.minibooking.repository;

import com.example.minibooking.model.responseToAd.ResponseToAd;

import java.util.List;

public interface ResponseToAdRepository extends BaseRepository<ResponseToAd, Long> {

    List<ResponseToAd> findAllByTenant(long tenantId, int pageSize, int pageNumber);

    List<ResponseToAd> findAllByRentalAd(long rentalAdId, int pageSize, int pageNumber);

    int countByRentalAdLandlord(long landlordId);

}
