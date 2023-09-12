package com.example.minibooking.service;

import com.example.minibooking.exception.BusinessException;
import com.example.minibooking.model.landlord.Landlord;
import com.example.minibooking.model.rentalAd.RentalAd;
import com.example.minibooking.model.rentalAd.RentalAdOwnDto;
import com.example.minibooking.model.rentalAd.RentalAdUpdateDto;
import com.example.minibooking.model.responseToAd.ResponseToAdDto;
import com.example.minibooking.repository.LandlordRepository;
import com.example.minibooking.repository.RentalAdRepository;
import com.example.minibooking.repository.ResponseToAdRepository;
import com.example.minibooking.security.LandlordPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalAdLandlordServiceImpl implements RentalAdLandlordService {

    private final RentalAdRepository rentalAdRepository;
    private final ResponseToAdRepository responseToAdRepository;
    private final LandlordRepository landlordRepository;

    public static final int RENTAL_ADS_PAGE_SIZE = 10;
    public static final int RESPONSE_TO_AD_SIZE = 5;


    @Override
    @Transactional
    public RentalAdOwnDto getOwnAdById(long id, LandlordPrincipal principal) {
        RentalAd rentalAd = rentalAdRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Rental Ad is not found"));
        validateAccess(rentalAd, principal);
        return RentalAdOwnDto.from(rentalAd);
    }

    @Override
    @Transactional
    public RentalAdOwnDto create(RentalAdUpdateDto dto, LandlordPrincipal principal) {
        Instant createAt = Instant.now();
        Landlord landlord = landlordRepository.getReferenceById(principal.getId());

        RentalAd rentalAd = new RentalAd()
                .setLandlord(landlord)
                .setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setPrice(dto.getPrice())
                .setCreatedAt(createAt)
                .setActive(dto.isActive());
        rentalAdRepository.create(rentalAd);
        return RentalAdOwnDto.from(rentalAd);
    }

    @Override
    @Transactional
    public RentalAdOwnDto update(long id, RentalAdUpdateDto dto, LandlordPrincipal principal) {
        RentalAd rentalAd = rentalAdRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Rental Ad is not found"));
        validateAccess(rentalAd, principal);
        rentalAd.setTitle(dto.getTitle());
        rentalAd.setDescription(dto.getDescription());
        rentalAd.setPrice(dto.getPrice());
        rentalAd.setActive(dto.isActive());

        return RentalAdOwnDto.from(rentalAd);


    }

    @Override
    @Transactional
    public List<RentalAdOwnDto> getPageOwnAds(int pageNumber, LandlordPrincipal principal) {
        return rentalAdRepository.findPageByLandlord(principal.getId(), RENTAL_ADS_PAGE_SIZE, pageNumber)
                .stream()
                .map(RentalAdOwnDto::from)
                .toList();
    }

    @Override
    @Transactional
    public List<ResponseToAdDto> getPageOfResponseToAdByOwnAds(int pageNumber, LandlordPrincipal principal) {
        return responseToAdRepository.findPageByLandlordWithRentalAdAndTenant(principal.getId(), RESPONSE_TO_AD_SIZE, pageNumber)
                .stream()
                .map(ResponseToAdDto::from)
                .toList();
    }

    private void validateAccess(RentalAd rentalAd, LandlordPrincipal principal) {
        long authenticated = principal.getId();
        long ownerId = rentalAd.getLandlord().getId();
        if (authenticated != ownerId) {
            throw new BusinessException("Access denied");
        }
    }
}
