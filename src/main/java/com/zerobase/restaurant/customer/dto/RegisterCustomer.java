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
    private String username;
    private String password;
    private String phoneNumber;

    public RegisterCustomer from(CustomerDto customerDto) {
        return RegisterCustomer.builder()
                .username(customerDto.getUsername())
                .password(customerDto.getPassword())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
    }
}
