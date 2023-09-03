package com.example.minibooking.service;

import com.example.minibooking.exception.BusinessException;
import com.example.minibooking.model.landlord.Landlord;
import com.example.minibooking.model.landlord.LandlordSignInDto;
import com.example.minibooking.model.landlord.LandlordSignUpDto;
import com.example.minibooking.repository.LandlordRepository;
import com.example.minibooking.security.AccessToken;
import com.example.minibooking.security.LandlordPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionOperations;

@Service
@RequiredArgsConstructor
public class LandlordServiceImpl implements LandlordService {

    private final LandlordRepository landlordRepository;
    private final AccessTokenService accessTokenService;
    private final PasswordEncoder passwordEncoder;
    private final TransactionOperations transactionOperations;

    @Override
    public AccessToken signIn(LandlordSignInDto dto) {
        Landlord landlord = landlordRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Такой email не найден"));
        if (!passwordEncoder.matches(dto.getPassword(), landlord.getPasswordHash())) {
            throw new BadCredentialsException("Неверный пароль");
        }
        LandlordPrincipal landlordPrincipal = LandlordPrincipal.from(landlord);
        return accessTokenService.generate(landlordPrincipal);
    }

    @Override
    public AccessToken signUp(LandlordSignUpDto dto) {
        String passwordHush = passwordEncoder.encode(dto.getPassword());
        Landlord landlord = this.create(dto, passwordHush);
        LandlordPrincipal landlordPrincipal = LandlordPrincipal.from(landlord);
        return accessTokenService.generate(landlordPrincipal);
    }

    private Landlord create(LandlordSignUpDto dto, String passwordHash) {
        return transactionOperations.execute(tx -> {
            boolean existsByEmail = landlordRepository.findByEmail(dto.getEmail()).isPresent();
            if (existsByEmail) {
                throw new BusinessException("Такой email уже существует");
            }
            Landlord landlord = new Landlord()
                    .setName(dto.getName())
                    .setEmail(dto.getEmail())
                    .setPasswordHash(passwordHash);
            landlordRepository.create(landlord);
            return landlord;
        });
    }
}
