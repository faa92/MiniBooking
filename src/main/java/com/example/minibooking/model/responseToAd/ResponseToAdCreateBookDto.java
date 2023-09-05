package com.example.minibooking.model.responseToAd;

import lombok.Value;

import java.time.LocalDate;

@Value
public class ResponseToAdCreateBookDto {
    long rentalAdId;
    String message;
    LocalDate dateFrom;
    LocalDate dateTo;
}
