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

    @PostMapping("/register/partner")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterPartner request) {
        return ResponseEntity.ok().body(
                request.from(this.partnerService.register(request))
        );
    }

    @GetMapping("/partner/info")
    public ResponseEntity<?> getPartnerInfo(@RequestParam("id") @Valid Long id) {
        return ResponseEntity.ok(this.partnerService.detailPartner(id));
    }


}
