package com.zerobase.restaurant.partner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPartner {
    private String email;
    private String password;
    private String username;

    public RegisterPartner from(PartnerDto partnerDto) {
        return RegisterPartner.builder()
                .email(partnerDto.getEmail())
                .password(partnerDto.getPassword())
                .username(partnerDto.getUsername())
                .build();
    }
}
