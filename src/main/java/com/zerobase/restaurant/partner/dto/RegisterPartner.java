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
    private String username;
    private String password;

    public RegisterPartner from(PartnerDto partnerDto) {
        return RegisterPartner.builder()
                .username(partnerDto.getUsername())
                .password(partnerDto.getPassword())
                .build();
    }
}