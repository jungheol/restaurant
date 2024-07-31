package com.zerobase.restaurant.customer.service;

import com.zerobase.restaurant.customer.dto.CustomerDto;
import com.zerobase.restaurant.customer.dto.RegisterCustomer;

public interface CustomerService {

    // 유저 회원 가입
    CustomerDto register(RegisterCustomer user);

}
