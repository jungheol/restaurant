package com.zerobase.restaurant.store.controller;

import com.zerobase.restaurant.store.dto.RegisterStore;
import com.zerobase.restaurant.store.dto.UpdateStore;
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
    public RegisterStore.Response registerStore(
            @RequestBody RegisterStore.Request request
    ) {
        return RegisterStore.Response.from(this.storeService.registerStore(request));
    }

    @PutMapping("/partner/update/{storeId}")
    public UpdateStore.Response updateStore(
            @PathVariable("storeId") Long storeId,
            @RequestBody UpdateStore.Request request
    ) {
        return UpdateStore.Response.from(this.storeService.updateStore(storeId, request));
    }

    @DeleteMapping("/partner/delete")
    public ResponseEntity<?> deleteStore(
            @RequestParam("storeId") Long storeId,
            @RequestParam("partnerId") Long partnerId
    ) {
        this.storeService.deleteStore(storeId, partnerId);
        return ResponseEntity.ok("매장 정보가 삭제되었습니다.");
    }

    @GetMapping("/customer/detail/{storeName}")
    public ResponseEntity<?> detailStore(@PathVariable("storeName") String storeName) {
        return ResponseEntity.ok(this.storeService.detailStore(storeName));
    }
}
