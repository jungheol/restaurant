package com.zerobase.restaurant.customer.repository;

import com.zerobase.restaurant.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByUsername(String username);
}
