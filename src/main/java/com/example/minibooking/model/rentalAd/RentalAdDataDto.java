package com.example.minibooking.model.rentalAd;

import com.example.minibooking.model.landlord.LandlordShortDto;
import lombok.Value;

@Value
public class RentalAdDataDto {
    long id;
    LandlordShortDto landlordShortDto;
    String title;
    boolean active;

    public static RentalAdDataDto from(RentalAd rentalAd) {
        return new RentalAdDataDto(
                rentalAd.getId(),
                LandlordShortDto.from(rentalAd.getLandlord()),
                rentalAd.getTitle(),
                rentalAd.isActive()
        );
    }
}
