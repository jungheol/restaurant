package com.zerobase.restaurant.store.dto;

import com.zerobase.restaurant.store.domain.Store;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailStore {
    private String storeName;
    private String address;
    private String description;

    public static DetailStore from(Store store) {
        return DetailStore.builder()
                .storeName(store.getStoreName())
                .address(store.getAddress())
                .description(store.getDescription())
                .build();
    }

}