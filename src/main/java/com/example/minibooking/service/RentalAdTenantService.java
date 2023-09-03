package com.example.minibooking.service;

import com.example.minibooking.model.rentalAd.RentalAdDataDto;
import com.example.minibooking.model.rentalAd.RentalAdPriceDto;
import com.example.minibooking.model.rentalAd.RentalAdShortDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalAdTenantService {
    List<RentalAdShortDto> findPageByLandlord(long landlordId, int pageSize, int pageNumber);

    List<Optional<RentalAdShortDto>> findPageActiveAdOfLandlord(long landlordId, int pageSize, int pageNumber);

    List<RentalAdPriceDto> findPageActiveAndLowPriceAd(int pageSize, int pageNumber);

    List<RentalAdDataDto> getAvailableAdsInDataRange(LocalDate start, LocalDate end, int pageSize, int pageNumber);
}
