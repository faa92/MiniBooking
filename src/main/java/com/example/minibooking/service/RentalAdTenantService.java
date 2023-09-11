package com.example.minibooking.service;

import com.example.minibooking.model.rentalAd.RentalAdDateDto;
import com.example.minibooking.model.rentalAd.RentalAdPriceDto;
import com.example.minibooking.model.rentalAd.RentalAdShortDto;
import com.example.minibooking.model.responseToAd.ResponseToAdCreateBookDto;
import com.example.minibooking.model.responseToAd.ResponseToAdShortBookDto;
import com.example.minibooking.security.TenantPrincipal;

import java.time.LocalDate;
import java.util.List;

public interface RentalAdTenantService {


//    List<RentalAdShortDto> getAllAdsByTitle(String title, int pageNumber);

    List<RentalAdShortDto> getPageByTitleQuery(String titleQuery, int pageNumber);


    List<RentalAdPriceDto> findPageActiveAndLowPriceAd(TenantPrincipal principal, int pageNumber);

    List<RentalAdDateDto> getAvailableAdsInDateRange(LocalDate start, LocalDate end, TenantPrincipal principal, int pageNumber);

    ResponseToAdShortBookDto sendBooking(ResponseToAdCreateBookDto dto, TenantPrincipal principal);
}
