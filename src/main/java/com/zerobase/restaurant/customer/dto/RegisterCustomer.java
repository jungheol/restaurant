package com.zerobase.restaurant.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCustomer {
    private String email;
    private String password;
    private String username;
    private String phoneNumber;

    public RegisterCustomer from(CustomerDto customerDto) {
        return RegisterCustomer.builder()
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .username(customerDto.getUsername())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
    }
}
