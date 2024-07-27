package com.zerobase.restaurant.auth.controller;

import com.zerobase.restaurant.auth.dto.Login;
import com.zerobase.restaurant.auth.security.TokenProvider;
import com.zerobase.restaurant.auth.service.AuthService;
import com.zerobase.restaurant.customer.domain.Customer;
import com.zerobase.restaurant.partner.domain.Partner;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenProvider tokenProvider;

    @PostMapping("/partner")
    public ResponseEntity<?> partnerLogin(@RequestBody @Valid Login request) {
        Partner partner = this.authService.authenticatePartner(request);
        return ResponseEntity.ok(
                this.tokenProvider.generateToken(
                        partner.getUsername(),
                        partner.getMemberType())
        );
    }

    @PostMapping("/customer")
    public ResponseEntity<?> customerLogin(@RequestBody @Valid Login request) {
        Customer customer = this.authService.authenticateCustomer(request);
        return ResponseEntity.ok(
                this.tokenProvider.generateToken(
                        customer.getUsername(),
                        customer.getMemberType())
        );
    }
}
