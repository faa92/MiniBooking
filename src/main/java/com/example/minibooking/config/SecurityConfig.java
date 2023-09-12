package com.example.minibooking.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.minibooking.security.AccessTokenProperties;
import com.example.minibooking.security.AccountRole;
import com.example.minibooking.service.AccessTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AccessTokenService accessTokenService) throws Exception {
        return httpSecurity
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(config -> config
                                .requestMatchers("/api-docs/**").permitAll()
                                .requestMatchers("/public-api/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/error").permitAll()
                                .requestMatchers(HttpMethod.POST, "/public-api/tenant-sign-up").permitAll()
                                .requestMatchers(HttpMethod.POST, "/public-api/tenant-sign-in").permitAll()
                                .requestMatchers(HttpMethod.POST, "/public-api/landlord-sign-up").permitAll()
                                .requestMatchers(HttpMethod.POST, "/public-api/landlord-sign-in").permitAll()
                                .requestMatchers(HttpMethod.GET, "/public-api/rental-ads").permitAll()

                                //TENANT
                                .requestMatchers(HttpMethod.GET, "/tenant-api/ads-date").hasRole(AccountRole.TENANT.name())
                                .requestMatchers(HttpMethod.GET, "/tenant-api/ads-low-price").hasRole(AccountRole.TENANT.name())
                                .requestMatchers(HttpMethod.POST, "/tenant-api/send-booking").hasRole(AccountRole.TENANT.name())

                                //LANDLORD
                                .requestMatchers(HttpMethod.POST, "/landlord-api/create-ads").hasRole(AccountRole.LANDLORD.name())
                                .requestMatchers(HttpMethod.PUT, "/landlord-api/update-ads/{rentalAdId}").hasRole(AccountRole.LANDLORD.name())
                                .requestMatchers(HttpMethod.GET, "/landlord-api/own-ad/{rentalAdId}").hasRole(AccountRole.LANDLORD.name())
                                .requestMatchers(HttpMethod.GET, "/landlord-api/own-ads").hasRole(AccountRole.LANDLORD.name())
                                .requestMatchers(HttpMethod.GET, "/landlord-api/bookings-own-ads").hasRole(AccountRole.LANDLORD.name())


//
//                                .requestMatchers("/**").authenticated()
//                                .anyRequest().denyAll()
                )
                .addFilterAfter(new AccessTokenAuthenticationFilter(accessTokenService), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    Algorithm jwtAlgorithm(AccessTokenProperties accessTokenProperties) {
        return Algorithm.HMAC256(accessTokenProperties.getSecret());
    }

    @Bean
    JWTVerifier jwtVerifier(Algorithm algorithm) {
        return JWT.require(algorithm).build();
    }

}
