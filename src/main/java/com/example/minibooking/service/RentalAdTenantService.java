package com.example.minibooking.service;

import com.example.minibooking.model.rentalAd.RentalAdDataDto;
import com.example.minibooking.model.rentalAd.RentalAdPriceDto;
import com.example.minibooking.model.rentalAd.RentalAdShortDto;
import com.example.minibooking.security.TenantPrincipal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalAdTenantService {

    List<RentalAdShortDto> getPageByTitleQuery(String titleQuery, int pageNumber);

    List<Optional<RentalAdShortDto>> findPageActiveAdsOfLandlord(long landlordId, TenantPrincipal principal, int pageNumber);

    List<RentalAdPriceDto> findPageActiveAndLowPriceAd(TenantPrincipal principal, int pageNumber);

    List<RentalAdDataDto> getAvailableAdsInDataRange(LocalDate start, LocalDate end, TenantPrincipal principal, int pageNumber);
}
