package com.example.minibooking.service;

import com.example.minibooking.exception.BusinessException;
import com.example.minibooking.model.rentalAd.RentalAd;
import com.example.minibooking.model.rentalAd.RentalAdDateDto;
import com.example.minibooking.model.rentalAd.RentalAdPriceDto;
import com.example.minibooking.model.rentalAd.RentalAdShortDto;
import com.example.minibooking.model.responseToAd.ResponseToAd;
import com.example.minibooking.model.responseToAd.ResponseToAdCreateBookDto;
import com.example.minibooking.model.responseToAd.ResponseToAdShortBookDto;
import com.example.minibooking.model.tenant.Tenant;
import com.example.minibooking.repository.RentalAdRepository;
import com.example.minibooking.repository.ResponseToAdRepository;
import com.example.minibooking.repository.TenantRepository;
import com.example.minibooking.security.TenantPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalAdTenantServiceImpl implements RentalAdTenantService {

    public static final int RENTAL_AD_PAGE_SIZE = 10;

    private final RentalAdRepository rentalAdRepository;
    private final ResponseToAdRepository responseToAdRepository;
    private final TenantRepository tenantRepository;


//    @Override
//    @Transactional
//    public List<RentalAdShortDto> getAllAdsByTitle(String title, int pageNumber) {
//        String dbTitleQuery = "%" + title + "%";
//        return rentalAdRepository.findAllAdsByTitle(dbTitleQuery, RENTAL_AD_PAGE_SIZE, pageNumber)
//                .stream()
//                .map(RentalAdShortDto::from)
//                .toList();
//    }

    @Override
    @Transactional
    public List<RentalAdShortDto> getPageByTitleQuery(String titleQuery, int pageNumber) {
        String dbTitleQuery = "%" + titleQuery + "%";
        return rentalAdRepository.findPageOfActiveListingsLandlordByTitle(dbTitleQuery, RENTAL_AD_PAGE_SIZE, pageNumber)
                .stream()
                .map(RentalAdShortDto::from)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<RentalAdPriceDto> findPageActiveAndLowPriceAd(TenantPrincipal principal, int pageNumber) {
        return rentalAdRepository.findPageActiveRentalAdByLowPrice(RENTAL_AD_PAGE_SIZE, pageNumber)
                .stream()
                .map(RentalAdPriceDto::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentalAdDateDto> getAvailableAdsInDateRange(LocalDate start, LocalDate end, TenantPrincipal principal, int pageNumber) {
        return rentalAdRepository.getAvailableRentalAdsInDateRange(start, end, RENTAL_AD_PAGE_SIZE, pageNumber)
                .stream()
                .map(RentalAdDateDto::from)
                .toList();
    }

    @Override
    public ResponseToAdShortBookDto sendBooking(ResponseToAdCreateBookDto dto, TenantPrincipal principal) {
        RentalAd rentalAd = rentalAdRepository.findById(dto.getRentalAdId())
                .orElseThrow(() -> new BusinessException("Объявление не не найдено"));

        Tenant tenant = tenantRepository.getReferenceById(principal.getId());
        Instant createAt = Instant.now();
        LocalDate from = dto.getDateFrom();
        LocalDate to = dto.getDateTo();
        ResponseToAd responseToAd = new ResponseToAd()
                .setTenant(tenant)
                .setRentalAd(rentalAd)
                .setMessage(dto.getMessage())
                .setDateFrom(from)
                .setDateTo(to)
                .setCreatedAt(createAt);
        responseToAdRepository.create(responseToAd);
        return ResponseToAdShortBookDto.from(responseToAd);  //todo
    }
}
