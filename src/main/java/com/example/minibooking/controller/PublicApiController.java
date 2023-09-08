package com.example.minibooking.controller;

import com.example.minibooking.model.landlord.LandlordSignInDto;
import com.example.minibooking.model.landlord.LandlordSignUpDto;
import com.example.minibooking.model.rentalAd.RentalAdShortDto;
import com.example.minibooking.model.tenant.TenantSignInDto;
import com.example.minibooking.model.tenant.TenantSignUpDto;
import com.example.minibooking.security.AccessToken;
import com.example.minibooking.service.LandlordService;
import com.example.minibooking.service.RentalAdTenantService;
import com.example.minibooking.service.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public-api")
@RequiredArgsConstructor
@Tag(name = "Public API", description = "Доступ без аунтификации")
public class PublicApiController {

    private final TenantService tenantService;
    private final RentalAdTenantService rentalAdTenantService;
    private final LandlordService landlordService;

    @PostMapping("/tenant-sign-up")
    public AccessToken signUpTenant(@RequestBody TenantSignUpDto dto) {
        return tenantService.signUp(dto);
    }

    @PostMapping("/tenant-sign-in")  // access token
    public AccessToken signInTenant(
            @RequestBody TenantSignInDto dto
    ) {
        return tenantService.signIn(dto);
    }

    @PostMapping("landlord-sign-up")
    public AccessToken signUpLandlord(@RequestBody LandlordSignUpDto dto) {
        return landlordService.signUp(dto);
    }

    @PostMapping("landlord-sign-in")
    public AccessToken signInLandlord(
            @RequestBody LandlordSignInDto dto
    ) {
        return landlordService.signIn(dto);
    }

    @GetMapping("/rental-ads")
    public List<RentalAdShortDto> getPageOfAdsByTitle(
            @RequestParam String query,
            @RequestParam int page
    ) {
        return rentalAdTenantService.getPageByTitleQuery(query, page);
    }
}
