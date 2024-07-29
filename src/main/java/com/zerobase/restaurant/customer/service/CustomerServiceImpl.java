package com.zerobase.restaurant.customer.service;

import com.zerobase.restaurant.auth.type.MemberType;
import com.zerobase.restaurant.common.exception.CustomException;
import com.zerobase.restaurant.customer.domain.Customer;
import com.zerobase.restaurant.customer.dto.CustomerDto;
import com.zerobase.restaurant.customer.dto.RegisterCustomer;
import com.zerobase.restaurant.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.restaurant.common.type.ErrorCode.ALREADY_EXISTED_CUSTOMER;
import static com.zerobase.restaurant.common.type.ErrorCode.CUSTOMER_NOT_FOUND;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerDto register(RegisterCustomer user) {
        boolean exists = this.customerRepository.existsByEmail(user.getEmail());
        if (exists) {
            throw new CustomException(ALREADY_EXISTED_CUSTOMER);
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Customer customer = this.customerRepository.save(Customer.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .memberType(MemberType.CUSTOMER)
                .phoneNumber(user.getPhoneNumber())
                .build());

        return CustomerDto.fromEntity(customer);
    }

    @Override
    public CustomerDto detailCustomer(Long userId) {
        Customer customer = this.customerRepository.findById(userId)
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));

        return CustomerDto.fromEntity(customer);
    }
}
