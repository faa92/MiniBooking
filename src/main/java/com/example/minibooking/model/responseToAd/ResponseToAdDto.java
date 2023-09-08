package com.example.minibooking.model.responseToAd;

import com.example.minibooking.model.rentalAd.RentalAdOwnDto;
import com.example.minibooking.model.tenant.TenantDto;
import lombok.Value;

import java.time.Instant;

@Value
public class ResponseToAdDto {
    long id;
    RentalAdOwnDto rentalAdOwnDto;
    TenantDto tenantDto;
    String message;
    Instant createAt;

    public static ResponseToAdDto from(ResponseToAd responseToAd) {
        return new ResponseToAdDto(
                responseToAd.getId(),
                RentalAdOwnDto.from(responseToAd.getRentalAd()),
                TenantDto.from(responseToAd.getTenant()),
                responseToAd.getMessage(),
                responseToAd.getCreatedAt()
        );
    }
}
