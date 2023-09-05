package com.example.minibooking.model.responseToAd;

import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;

@Value
public class ResponseToAdShortBookDto {
    long id;
    String message;
    LocalDate dateFrom;
    LocalDate dateTo;
    Instant createAt;

    public static ResponseToAdShortBookDto from(ResponseToAd responseToAd) {
        return new ResponseToAdShortBookDto(
                responseToAd.getId(),
                responseToAd.getMessage(),
                responseToAd.getDateFrom(),
                responseToAd.getDateTo(),
                responseToAd.getCreatedAt()
        );
    }
}
