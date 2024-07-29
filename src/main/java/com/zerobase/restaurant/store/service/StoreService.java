package com.zerobase.restaurant.store.service;

import com.zerobase.restaurant.store.dto.DetailStore;
import com.zerobase.restaurant.store.dto.RegisterStore;
import com.zerobase.restaurant.store.dto.StoreDto;
import com.zerobase.restaurant.store.dto.UpdateStore;

public interface StoreService {
    StoreDto registerStore(RegisterStore.Request request);

    void deleteStore(Long storeId, Long partnerId);

    DetailStore detailStore(String storeName);

    StoreDto updateStore(Long storeId, UpdateStore.Request request);
}
