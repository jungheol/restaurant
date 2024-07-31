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

    /**
     * 식당 등록
     * @param request 식당 등록 요청
     *                - partnerId : 파트너 id
     *                - storeName : 식당 이름
     *                - address : 식당 주소
     *                - description : 식당 정보
     * @return 식당 등록 결과
     *                - storeName : 식당 이름
     *                - address : 식당 주소
     *                - description : 식당 정보
     */
    @PostMapping("/partner/register")
    public RegisterStore.Response registerStore(
            @RequestBody RegisterStore.Request request
    ) {
        return RegisterStore.Response.from(this.storeService.registerStore(request));
    }

    /**
     * 식당 정보 수정
     * @param request 식당 정보 수정 요청
     *                - partnerId : 파트너 id
     *                - storeName : 식당 이름
     *                - address : 식당 주소
     *                - description : 식당 정보
     * @return 식당 정보 수정 결과
     *                - storeName : 식당 이름
     *                - address : 식당 주소
     *                - description : 식당 정보
     */
    @PutMapping("/partner/update/{storeId}")
    public UpdateStore.Response updateStore(
            @PathVariable("storeId") Long storeId,
            @RequestBody UpdateStore.Request request
    ) {
        return UpdateStore.Response.from(this.storeService.updateStore(storeId, request));
    }

    /**
     * 식당 삭제
     * @param storeId : 예약 식당 id
     *        partnerId : 파트너 id
     * @return 식당 삭제 결과
     *                - "식당 정보가 삭제되었습니다."
     */
    @DeleteMapping("/partner/delete")
    public ResponseEntity<?> deleteStore(
            @RequestParam("storeId") Long storeId,
            @RequestParam("partnerId") Long partnerId
    ) {
        this.storeService.deleteStore(storeId, partnerId);
        return ResponseEntity.ok("식당 정보가 삭제되었습니다.");
    }

    /**
     * 식당 정보 조회
     * @return 식당 정보 조회 결과
     *                - storeName : 식당 이름
     *                - address : 식당 주소
     *                - description : 식당 정보
     */
    @GetMapping("/customer/detail/{storeName}")
    public ResponseEntity<?> detailStore(@PathVariable("storeName") String storeName) {
        return ResponseEntity.ok(this.storeService.detailStore(storeName));
    }
}
