package com.example.minibooking.controller;

import com.example.minibooking.model.rentalAd.RentalAdDataDto;
import com.example.minibooking.model.rentalAd.RentalAdPriceDto;
import com.example.minibooking.model.responseToAd.ResponseToAdCreateBookDto;
import com.example.minibooking.model.responseToAd.ResponseToAdShortBookDto;
import com.example.minibooking.security.TenantPrincipal;
import com.example.minibooking.service.RentalAdTenantService;
import com.example.minibooking.service.TenantService;
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
    private final TenantService tenantService;


    @GetMapping("/find-by-data")
    public List<RentalAdDataDto> findByData(
            @RequestBody LocalDate start,//todo
            @RequestBody LocalDate end,//todo
            @AuthenticationPrincipal TenantPrincipal principal,
            @RequestParam int pageNumber
    ) {
        return rentalAdTenantService.getAvailableAdsInDataRange(start, end, principal, pageNumber);
    }

    @GetMapping("/find-by-low-price")
    public List<RentalAdPriceDto> findByLowPrice(
            @AuthenticationPrincipal TenantPrincipal tenantPrincipal,
            @RequestParam int pageNumber
    ) {
        return rentalAdTenantService.findPageActiveAndLowPriceAd(tenantPrincipal, pageNumber);

    }

    @PostMapping("/send-book")
    public ResponseToAdShortBookDto sendBooking(
            @RequestBody ResponseToAdCreateBookDto dto,
            @AuthenticationPrincipal TenantPrincipal principal
    ) {
        return rentalAdTenantService.sendBooking(dto, principal);
    }
}
