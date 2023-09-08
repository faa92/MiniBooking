package com.example.minibooking.model.responseToAd;

import com.example.minibooking.model.tenant.Tenant;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class ResponseToAdConfirmBookingDto {
    Tenant tenant;
    ResponseToAd responseToAd;
    String message;
    BigDecimal price;
}
