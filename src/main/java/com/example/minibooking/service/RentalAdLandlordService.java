package com.example.minibooking.service;

import com.example.minibooking.model.rentalAd.RentalAdOwnDto;
import com.example.minibooking.model.rentalAd.RentalAdShortDto;
import com.example.minibooking.model.rentalAd.RentalAdUpdateDto;
import com.example.minibooking.security.LandlordPrincipal;

import java.util.List;

public interface RentalAdLandlordService {

    //    подтвердить бронирование + отправить письмо
    RentalAdOwnDto create(RentalAdUpdateDto dto, LandlordPrincipal principal);

    RentalAdOwnDto update(long id, RentalAdUpdateDto dto, LandlordPrincipal principal);

    List<RentalAdOwnDto> getPageOwnAds(int pageNumber, LandlordPrincipal principal);

    List<RentalAdShortDto> getPageOfBookingsByAd(long rentalAdId, LandlordPrincipal principal);


}
