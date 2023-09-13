package com.example.minibooking.service;

import com.example.minibooking.model.rentalAd.RentalAdDateDto;
import com.example.minibooking.model.rentalAd.RentalAdPriceDto;
import com.example.minibooking.model.rentalAd.RentalAdShortDto;
import com.example.minibooking.model.responseToAd.ResponseToAdCreateBookDto;
import com.example.minibooking.model.responseToAd.ResponseToAdShortBookingDto;
import com.example.minibooking.security.TenantPrincipal;

import java.time.LocalDate;
import java.util.List;

public interface RentalAdTenantService {
    List<RentalAdShortDto> getPageByTitleQuery(String titleQuery, int pageNumber);

    List<RentalAdPriceDto> findPageActiveAndLowPriceAd(TenantPrincipal principal, int pageNumber);

    List<RentalAdDateDto> getAvailableAdsInDateRange(LocalDate start, LocalDate end, TenantPrincipal principal, int pageNumber);

    ResponseToAdShortBookingDto sendBooking(ResponseToAdCreateBookDto dto, TenantPrincipal principal);
}
