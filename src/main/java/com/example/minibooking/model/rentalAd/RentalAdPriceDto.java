package com.example.minibooking.model.rentalAd;

import com.example.minibooking.model.landlord.LandlordShortDto;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class RentalAdPriceDto {
    long id;
    LandlordShortDto landlordShortDto;
    String title;
    boolean active;
    BigDecimal price;

    public static RentalAdPriceDto from(RentalAd rentalAd) {
        return new RentalAdPriceDto(
                rentalAd.getId(),
                LandlordShortDto.from(rentalAd.getLandlord()),
                rentalAd.getTitle(),
                rentalAd.isActive(),
                rentalAd.getPrice()
        );
    }
}
