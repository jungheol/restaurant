package com.zerobase.restaurant.store.dto;

import com.zerobase.restaurant.partner.domain.Partner;
import com.zerobase.restaurant.store.domain.Store;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    private Partner partner;
    private String storeName;
    private String address;
    private String description;

    public static StoreDto fromEntity(Store store) {
        return StoreDto.builder()
                .partner(store.getPartner())
                .storeName(store.getStoreName())
                .address(store.getAddress())
                .description(store.getDescription())
                .build();
    }
}
