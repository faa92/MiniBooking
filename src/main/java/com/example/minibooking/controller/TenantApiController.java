package com.example.minibooking.controller;

import com.example.minibooking.model.rentalAd.RentalAdDateDto;
import com.example.minibooking.model.rentalAd.RentalAdPriceDto;
import com.example.minibooking.model.responseToAd.ResponseToAdCreateBookDto;
import com.example.minibooking.model.responseToAd.ResponseToAdShortBookingDto;
import com.example.minibooking.security.TenantPrincipal;
import com.example.minibooking.service.RentalAdTenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tenant-api")
@RequiredArgsConstructor
@Tag(name = "Tenant API", description = "Возможности арендатора")
public class TenantApiController {

    private final RentalAdTenantService rentalAdTenantService;


    @GetMapping("/ads-date")
    public List<RentalAdDateDto> findByDate(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end,
            @AuthenticationPrincipal TenantPrincipal principal,
            @RequestParam int pageNumber
    ) {
        return rentalAdTenantService.getAvailableAdsInDateRange(start, end, principal, pageNumber);
    }

    @GetMapping("/ads-low-price")
    public List<RentalAdPriceDto> findByLowPrice(
            @AuthenticationPrincipal TenantPrincipal tenantPrincipal,
            @RequestParam int pageNumber
    ) {
        return rentalAdTenantService.findPageActiveAndLowPriceAd(tenantPrincipal, pageNumber);
    }

    @PostMapping("/send-booking")
    public ResponseToAdShortBookingDto sendBooking(
            @RequestBody ResponseToAdCreateBookDto dto,
            @AuthenticationPrincipal TenantPrincipal principal
    ) {
        return rentalAdTenantService.sendBooking(dto, principal);
    }
}
