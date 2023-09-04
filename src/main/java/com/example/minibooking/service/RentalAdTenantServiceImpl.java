package com.example.minibooking.service;

import com.example.minibooking.model.rentalAd.RentalAdDataDto;
import com.example.minibooking.model.rentalAd.RentalAdPriceDto;
import com.example.minibooking.model.rentalAd.RentalAdShortDto;
import com.example.minibooking.repository.RentalAdRepository;
import com.example.minibooking.repository.ResponseToAdRepository;
import com.example.minibooking.repository.TenantRepository;
import com.example.minibooking.security.TenantPrincipal;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalAdTenantServiceImpl implements RentalAdTenantService {

    public static final int RENTAL_AD_PAGE_SIZE = 10;

    private final RentalAdRepository rentalAdRepository;
    private final ResponseToAdRepository responseToAdRepository;
    private final TenantRepository tenantRepository;


    @Override
    @Transactional(readOnly = true)
    public List<RentalAdShortDto> getPageByTitleQuery(String titleQuery, int pageNumber) {
        String dbTitleQuery = "%" + titleQuery + "%";
        return rentalAdRepository.findPageOfActiveListingsLandlordByTitle(dbTitleQuery, RENTAL_AD_PAGE_SIZE, pageNumber)
                .stream()
                .map(RentalAdShortDto::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Optional<RentalAdShortDto>> findPageActiveAdsOfLandlord(long landlordId, TenantPrincipal principal, int pageNumber) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentalAdPriceDto> findPageActiveAndLowPriceAd(@Nullable TenantPrincipal principal, int pageNumber) {
//        if (principal.getRole() != AccountRole.TENANT){   //todo
//            throw new BusinessException("Нужна аутентификация");
//        }
        return rentalAdRepository.findPageActiveRentalAdByLowPrice(RENTAL_AD_PAGE_SIZE, pageNumber)
                .stream()
                .map(RentalAdPriceDto::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentalAdDataDto> getAvailableAdsInDataRange(LocalDate start, LocalDate end, TenantPrincipal principal, int pageNumber) {
        return null;
    }
}
