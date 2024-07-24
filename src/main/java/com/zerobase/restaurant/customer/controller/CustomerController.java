package com.zerobase.restaurant.customer.controller;

import com.zerobase.restaurant.customer.dto.RegisterCustomer;
import com.zerobase.restaurant.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register/customer")
    public ResponseEntity<?> register(@RequestBody RegisterCustomer request) {
        return ResponseEntity.ok().body(
                request.from(this.customerService.register(request))
        );
    }

    @GetMapping("/customer/info")
    public ResponseEntity<?> getCustomerInfo(@RequestParam("id") Long id) {
        return ResponseEntity.ok(this.customerService.detailCustomer(id));
    }
}
