package com.example.minibooking.model.rentalAd;

import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
public class RentalAdOwnDto {

    long id;
    String title;
    String description;
    BigDecimal price;
    boolean active;    //todo
    Instant createdAt;

    public static RentalAdOwnDto from(RentalAd rentalAd) {
        return new RentalAdOwnDto(
                rentalAd.getId(),
                rentalAd.getTitle(),
                rentalAd.getDescription(),
                rentalAd.getPrice(),
                rentalAd.isActive(),
                rentalAd.getCreatedAt()
        );
    }
}
