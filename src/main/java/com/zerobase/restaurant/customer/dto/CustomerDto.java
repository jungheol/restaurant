package com.zerobase.restaurant.customer.dto;

import com.zerobase.restaurant.auth.type.MemberType;
import com.zerobase.restaurant.customer.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private MemberType memberType;
    private String phoneNumber;

    public static CustomerDto fromEntity(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .username(customer.getUsername())
                .memberType(customer.getMemberType())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
