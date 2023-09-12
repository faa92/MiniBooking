package com.example.minibooking.model.responseToAd;

import com.example.minibooking.model.rentalAd.RentalAd;
import com.example.minibooking.model.tenant.Tenant;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class ResponseToAdConfirmBookingDto {
    Tenant tenant;
    RentalAd rentalAd;
    String message;
    BigDecimal price;

    public ResponseToAdConfirmBookingDto from(ResponseToAd responseToAd) {
        return new ResponseToAdConfirmBookingDto(
                responseToAd.getTenant(),
                responseToAd.getRentalAd(),
                responseToAd.getMessage(),
                responseToAd.getRentalAd().getPrice()
        );
    }
}
