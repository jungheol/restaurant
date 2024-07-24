package com.zerobase.restaurant.store.service;

import com.zerobase.restaurant.store.dto.RegisterStore;
import com.zerobase.restaurant.store.dto.StoreDto;

public interface StoreService {
    StoreDto registerStore(RegisterStore.Request request);

    void deleteStore(Long storeId, Long partnerId);
}
