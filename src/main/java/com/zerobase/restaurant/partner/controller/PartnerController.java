package com.zerobase.restaurant.partner.controller;

import com.zerobase.restaurant.partner.dto.RegisterPartner;
import com.zerobase.restaurant.partner.service.PartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    /**
     * 파트너 회원 가입
     * @param request 파트너 회원 가입 요청
     *                - email : 이메일
     *                - password : 비밀번호
     *                - username : 이름
     * @return 파트너 회원 가입 결과
     *                - email : 이메일
     *                - password : 암호화 된 비밀번호
     *                - username : 이름
     */
    @PostMapping("/register/partner")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterPartner request) {
        return ResponseEntity.ok().body(
                request.from(this.partnerService.register(request))
        );
    }
}
