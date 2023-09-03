package com.example.minibooking.model.rentalAd;

import com.example.minibooking.model.landlord.LandlordShortDto;
import lombok.Value;

@Value
public class RentalAdShortDto {
    long id;
    LandlordShortDto landlordShortDto;
    String title;

    public static RentalAdShortDto from(RentalAd rentalAd) {
        return new RentalAdShortDto(
                rentalAd.getId(),
                LandlordShortDto.from(rentalAd.getLandlord()),
                rentalAd.getTitle()
        );
    }

}
