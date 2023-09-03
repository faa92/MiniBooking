package com.example.minibooking.service;

import com.example.minibooking.model.rentalAd.RentalAdDataDto;
import com.example.minibooking.model.rentalAd.RentalAdPriceDto;
import com.example.minibooking.model.rentalAd.RentalAdShortDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RentalAdTenantServiceImpl implements RentalAdTenantService {
    @Override
    public List<RentalAdShortDto> findPageByLandlord(long landlordId, int pageSize, int pageNumber) {
        return null;
    }

    @Override
    public List<Optional<RentalAdShortDto>> findPageActiveAdOfLandlord(long landlordId, int pageSize, int pageNumber) {
        return null;
    }

    @Override
    public List<RentalAdPriceDto> findPageActiveAndLowPriceAd(int pageSize, int pageNumber) {
        return null;
    }

    @Override
    public List<RentalAdDataDto> getAvailableAdsInDataRange(LocalDate start, LocalDate end, int pageSize, int pageNumber) {
        return null;
    }
}
