package com.example.minibooking.service;

import com.example.minibooking.model.rentalAd.RentalAdOwnDto;
import com.example.minibooking.model.rentalAd.RentalAdUpdateDto;
import com.example.minibooking.model.responseToAd.ResponseToAdBookingDto;
import com.example.minibooking.model.responseToAd.ResponseToAdConfirmBookingDto;
import com.example.minibooking.model.responseToAd.ResponseToAdDto;
import com.example.minibooking.security.LandlordPrincipal;

import java.util.List;

public interface RentalAdLandlordService {

    //    подтвердить бронирование + отправить письмо -confirm booking
    ResponseToAdConfirmBookingDto confirmBooking(ResponseToAdBookingDto dto, LandlordPrincipal principal);

    RentalAdOwnDto getOwnAdById(long id, LandlordPrincipal principal);

    RentalAdOwnDto create(RentalAdUpdateDto dto, LandlordPrincipal principal);

    RentalAdOwnDto update(long id, RentalAdUpdateDto dto, LandlordPrincipal principal);

    List<RentalAdOwnDto> getPageOwnAds(int pageNumber, LandlordPrincipal principal);

    List<ResponseToAdDto> getPageOfResponseToAdByOwnAds(int pageNumber, LandlordPrincipal principal);

}
