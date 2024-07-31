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

    /**
     * 유저 회원 가입
     * @param request 유저 회원 가입 요청
     *                - email : 이메일
     *                - password : 비밀번호
     *                - username : 이름
     *                - phoneNumber : 핸드폰 번호
     * @return 유저 회원 가입 결과
     *                - email : 이메일
     *                - password : 암호화 된 비밀번호
     *                - username : 이름
     *                - phoneNumber : 핸드폰 번호
     */
    @PostMapping("/register/customer")
    public ResponseEntity<?> register(@RequestBody RegisterCustomer request) {
        return ResponseEntity.ok().body(
                request.from(this.customerService.register(request))
        );
    }
}
