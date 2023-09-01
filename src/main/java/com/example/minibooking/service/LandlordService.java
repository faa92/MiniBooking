package com.example.minibooking.service;

import com.example.minibooking.model.landlord.LandlordSignInDto;
import com.example.minibooking.model.landlord.LandlordSignUpDto;
import com.example.minibooking.security.AccessToken;

public interface LandlordService {
    AccessToken signIn(LandlordSignInDto dto);

    AccessToken signUp(LandlordSignUpDto dto);
}
