package com.zerobase.restaurant.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RegisterStore {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long partnerId;

        private String storeName;

        private String address;

        private String description;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String storeName;

        public static Response from(StoreDto storeDto) {
            return Response.builder()
                    .storeName(storeDto.getStoreName())
                    .build();
        }
    }
}
