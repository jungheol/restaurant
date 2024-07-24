package com.zerobase.restaurant.store.controller;

import com.zerobase.restaurant.store.dto.RegisterStore;
import com.zerobase.restaurant.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/partner/register")
    @PreAuthorize("hasRole('PARTNER')")
    public RegisterStore.Response registerStore(
            @RequestBody RegisterStore.Request request
    ) {
        return RegisterStore.Response.from(this.storeService.registerStore(request));
    }

    @DeleteMapping("/partner/delete")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity<?> deleteStore(
            @RequestParam("id") Long partnerId,
            @RequestParam("store") Long storeId
    ) {
        this.storeService.deleteStore(storeId, partnerId);
        return ResponseEntity.ok("매장 정보가 삭제되었습니다.");
    }

    @GetMapping("/detail/{storeName}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> detailStore(@PathVariable String storeName) {
        return ResponseEntity.ok(this.storeService.detailStore(storeName));
    }
}
