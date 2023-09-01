package com.example.minibooking.repository;

import com.example.minibooking.model.landlord.Landlord;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LandlordJpaRepository
        extends BaseJpaRepository<Landlord, Long>
        implements LandlordRepository {

    public LandlordJpaRepository() {
        super(Landlord.class);
    }

    @Override
    public Optional<Landlord> findByEmail(String email) {
        return entityManager.createQuery("""
                            SELECT landlord
                            FROM Landlord landlord
                            WHERE lower(landlord.email) = lower(:email) 
                        """, Landlord.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
}
