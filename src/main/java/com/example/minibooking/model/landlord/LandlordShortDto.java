package com.example.minibooking.model.landlord;

import lombok.Value;

@Value
public class LandlordShortDto {
    long id;
    String name;
    String email;

    public static LandlordShortDto from(Landlord landlord) {
        return new LandlordShortDto(
                landlord.getId(),
                landlord.getName(),
                landlord.getEmail()
        );
    }
}
