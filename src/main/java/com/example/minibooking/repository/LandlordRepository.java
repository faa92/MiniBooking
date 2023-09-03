package com.example.minibooking.repository;

import com.example.minibooking.model.landlord.Landlord;

import java.util.Optional;

public interface LandlordRepository extends BaseRepository<Landlord, Long> {

    Optional<Landlord> findByEmail(String email);
}
