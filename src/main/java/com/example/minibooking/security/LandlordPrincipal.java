package com.example.minibooking.security;

import com.example.minibooking.model.landlord.Landlord;
import lombok.Value;

@Value
public class LandlordPrincipal implements AccountPrincipal {

    long id;

    @Override
    public AccountRole getRole() {
        return AccountRole.LANDLORD;
    }

    public static LandlordPrincipal from(Landlord landlord) {
        return new LandlordPrincipal(landlord.getId());
    }
}
