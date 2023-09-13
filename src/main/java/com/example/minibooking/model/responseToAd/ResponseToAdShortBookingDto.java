package com.example.minibooking.model.responseToAd;

import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;

@Value
public class ResponseToAdShortBookingDto {
    long id;
    String message;
    LocalDate dateFrom;
    LocalDate dateTo;
    Instant createAt;

    public static ResponseToAdShortBookingDto from(ResponseToAd responseToAd) {
        return new ResponseToAdShortBookingDto(
                responseToAd.getId(),
                responseToAd.getMessage(),
                responseToAd.getDateFrom(),
                responseToAd.getDateTo(),
                responseToAd.getCreatedAt()
        );
    }
}
