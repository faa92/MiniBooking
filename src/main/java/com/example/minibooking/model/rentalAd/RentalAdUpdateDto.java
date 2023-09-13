package com.example.minibooking.model.rentalAd;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class RentalAdUpdateDto {
    String title;
    String description;
    BigDecimal price;
    boolean active;
}
