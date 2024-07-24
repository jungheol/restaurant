package com.zerobase.restaurant.customer.service;

import com.zerobase.restaurant.customer.dto.CustomerDto;
import com.zerobase.restaurant.customer.dto.RegisterCustomer;

public interface CustomerService {

    CustomerDto register(RegisterCustomer user);

    CustomerDto detailCustomer(Long userId);
}
