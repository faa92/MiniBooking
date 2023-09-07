package com.example.minibooking.service;

import com.example.minibooking.model.rentalAd.RentalAdOwnDto;
import com.example.minibooking.model.rentalAd.RentalAdShortDto;
import com.example.minibooking.model.rentalAd.RentalAdUpdateDto;
import com.example.minibooking.repository.LandlordRepository;
import com.example.minibooking.repository.RentalAdRepository;
import com.example.minibooking.repository.ResponseToAdRepository;
import com.example.minibooking.security.LandlordPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalAdLandlordServiceImpl implements RentalAdLandlordService {

    private final RentalAdRepository rentalAdRepository;
    private final ResponseToAdRepository responseToAdRepository;
    private final LandlordRepository landlordRepository;

    @Override
    @Transactional
    public RentalAdOwnDto create(RentalAdUpdateDto dto, LandlordPrincipal principal) {
        return null;
    }

    @Override
    @Transactional
    public RentalAdOwnDto update(long id, RentalAdUpdateDto dto, LandlordPrincipal principal) {
        return null;
    }

    @Override
    @Transactional
    public List<RentalAdOwnDto> getPageOwnAds(int pageNumber, LandlordPrincipal principal) {
        return null;
    }

    @Override
    @Transactional
    public List<RentalAdShortDto> getPageOfBookingsByAd(long rentalAdId, LandlordPrincipal principal) {
        return null;
    }
}
