package com.example.minibooking.service;

import com.example.minibooking.exception.BusinessException;
import com.example.minibooking.model.tenant.Tenant;
import com.example.minibooking.model.tenant.TenantSignInDto;
import com.example.minibooking.model.tenant.TenantSignUpDto;
import com.example.minibooking.repository.TenantRepository;
import com.example.minibooking.security.AccessToken;
import com.example.minibooking.security.TenantPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionOperations;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final AccessTokenService accessTokenService;
    private final PasswordEncoder passwordEncoder;
    private final TransactionOperations transactionOperations;

    @Override
    public AccessToken signIn(TenantSignInDto dto) {
        Tenant tenant = tenantRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Такой email не найден"));
        if (!passwordEncoder.matches(dto.getPassword(), tenant.getPasswordHash())) {
            throw new BadCredentialsException("Неверный пароль");
        }
        TenantPrincipal tenantPrincipal = TenantPrincipal.from(tenant);
        return accessTokenService.generate(tenantPrincipal);
    }

    @Override
    public AccessToken signUp(TenantSignUpDto dto) {
        String passwordHash = passwordEncoder.encode(dto.getPassword());
        Tenant tenant = create(dto, passwordHash);
        TenantPrincipal tenantPrincipal = TenantPrincipal.from(tenant);
        return accessTokenService.generate(tenantPrincipal);
    }

    private Tenant create(TenantSignUpDto dto, String passwordHush) {
        return transactionOperations.execute(tx -> {
            boolean existByEmail = tenantRepository
                    .findByEmail(dto.getEmail()).isPresent();
            if (existByEmail) {
                throw new BusinessException("Такой email уже существует");
            }
            Tenant tenant = new Tenant()
                    .setName(dto.getName())
                    .setEmail(dto.getEmail())
                    .setPasswordHash(passwordHush);
            tenantRepository.create(tenant);
            return tenant;
        });
    }
}
