package com.zerobase.restaurant.store.service;

import com.zerobase.restaurant.store.dto.DetailStore;
import com.zerobase.restaurant.store.dto.RegisterStore;
import com.zerobase.restaurant.store.dto.StoreDto;
import com.zerobase.restaurant.store.dto.UpdateStore;

public interface StoreService {

    // 식당 등록
    StoreDto registerStore(RegisterStore.Request request);

    // 식당 삭제
    void deleteStore(Long storeId, Long partnerId);

    // 식당 정보 조회
    DetailStore detailStore(String storeName);

    // 식당 정보 수정
    StoreDto updateStore(Long storeId, UpdateStore.Request request);
}
