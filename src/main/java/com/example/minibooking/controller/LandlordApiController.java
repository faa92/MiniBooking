package com.example.minibooking.controller;

import com.example.minibooking.model.rentalAd.RentalAdOwnDto;
import com.example.minibooking.model.rentalAd.RentalAdUpdateDto;
import com.example.minibooking.model.responseToAd.ResponseToAdDto;
import com.example.minibooking.repository.ResponseToAdRepository;
import com.example.minibooking.security.LandlordPrincipal;
import com.example.minibooking.service.LandlordService;
import com.example.minibooking.service.RentalAdLandlordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/landlord-api")
@RequiredArgsConstructor
@Tag(name = "Landlord API", description = "Возможности арендодателя")
public class LandlordApiController {

    private final LandlordService landlordService;
    private final RentalAdLandlordService rentalAdLandlordService;
    private final ResponseToAdRepository responseToAdRepository;

    @GetMapping("/own-ad")
    public RentalAdOwnDto getOwnAd(
            @PathVariable long rentalAdId,
            @AuthenticationPrincipal LandlordPrincipal principal
    ) {
        return rentalAdLandlordService.getOwnAdById(rentalAdId, principal);
    }

    @GetMapping("/own-rental-ads")
    public List<RentalAdOwnDto> getPageOwnAds(
            @RequestParam int page,
            @AuthenticationPrincipal LandlordPrincipal principal
    ) {
        return rentalAdLandlordService.getPageOwnAds(page, principal);
    }

    @GetMapping("/bookings-own_ads")
    public List<ResponseToAdDto> getPageBookingsByOwnRentalAds(
            @RequestParam int page,
            @AuthenticationPrincipal LandlordPrincipal principal
    ) {
        return rentalAdLandlordService.getPageOfResponseToAdByOwnAds(page, principal);
    }

    @GetMapping("/rental-ad-update/{rentalAdId}")
    public RentalAdOwnDto updateAd(
            @PathVariable long rentalAdId,
            @RequestParam RentalAdUpdateDto dto,
            @AuthenticationPrincipal LandlordPrincipal principal
    ) {
        return rentalAdLandlordService.update(rentalAdId, dto, principal);
    }

    @PostMapping("/rental-ad-create")
    public RentalAdOwnDto createAd(
            @RequestBody RentalAdUpdateDto dto,
            @AuthenticationPrincipal LandlordPrincipal principal
    ) {
        return rentalAdLandlordService.create(dto, principal);
    }


}
