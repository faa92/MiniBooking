package com.example.minibooking.model.rentalAd;

import com.example.minibooking.model.landlord.LandlordShortDto;
import lombok.Value;

@Value
public class RentalAdDateDto {
    long id;
    LandlordShortDto landlordShortDto;
    String title;
    boolean active;

    public static RentalAdDateDto from(RentalAd rentalAd) {
        return new RentalAdDateDto(
                rentalAd.getId(),
                LandlordShortDto.from(rentalAd.getLandlord()),
                rentalAd.getTitle(),
                rentalAd.isActive()
        );
    }
}
