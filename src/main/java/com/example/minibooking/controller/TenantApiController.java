package com.example.minibooking.controller;

import com.example.minibooking.model.responseToAd.ResponseToAdCreateBookDto;
import com.example.minibooking.model.responseToAd.ResponseToAdShortBookDto;
import com.example.minibooking.security.TenantPrincipal;
import com.example.minibooking.service.RentalAdTenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant-api")
@RequiredArgsConstructor
@Tag(name = "Tenant API", description = "какое-то  описание")
public class TenantApiController {

    private final RentalAdTenantService rentalAdTenantService;

    @PostMapping("/response-to-ads")
    public ResponseToAdShortBookDto sendBooking(
            @RequestBody ResponseToAdCreateBookDto dto,
            @AuthenticationPrincipal TenantPrincipal principal
    ) {
        return rentalAdTenantService.sendBook(dto, principal);
    }
}
