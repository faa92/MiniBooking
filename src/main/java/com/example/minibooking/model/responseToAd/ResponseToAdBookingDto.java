package com.example.minibooking.model.responseToAd;

import lombok.Value;

import java.time.LocalDate;

@Value
public class ResponseToAdBookingDto {
    long rentalAdId;
    LocalDate dateFrom;
    LocalDate dateTo;
    String message;

}
