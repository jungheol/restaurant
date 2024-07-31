package com.zerobase.restaurant.store.service;

import com.zerobase.restaurant.common.exception.CustomException;
import com.zerobase.restaurant.partner.domain.Partner;
import com.zerobase.restaurant.partner.repository.PartnerRepository;
import com.zerobase.restaurant.store.domain.Store;
import com.zerobase.restaurant.store.dto.DetailStore;
import com.zerobase.restaurant.store.dto.RegisterStore;
import com.zerobase.restaurant.store.dto.StoreDto;
import com.zerobase.restaurant.store.dto.UpdateStore;
import com.zerobase.restaurant.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.restaurant.common.type.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final PartnerRepository partnerRepository;

    // 식당 등록
    @Override
    @Transactional
    public StoreDto registerStore(RegisterStore.Request request) {
        Partner partner = this.partnerRepository.findById(request.getPartnerId())
                        .orElseThrow(() -> new CustomException(PARTNER_NOT_FOUND));

        if (this.storeRepository.existsByStoreName(request.getStoreName())) {
            throw new CustomException(ALREADY_EXISTED_RESTAURANT);
        }

        return StoreDto.fromEntity(this.storeRepository.save(Store.builder()
                .partner(partner)
                .storeName(request.getStoreName())
                .address(request.getAddress())
                .description(request.getDescription())
                .build()));
    }

    // 식당 삭제
    @Override
    @Transactional
    public void deleteStore(Long storeId, Long partnerId) {
        Store store = this.storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

        if (!store.getPartner().getId().equals(partnerId)) {
            throw new CustomException(STORE_NOT_MATCH_MANAGER);
        }

        this.storeRepository.delete(store);
    }

    // 식당 정보 수정
    @Override
    @Transactional
    public StoreDto updateStore(Long storeId, UpdateStore.Request request) {
        Store store = this.storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

        if (!store.getPartner().getId().equals(request.getPartnerId())) {
            throw new CustomException(STORE_NOT_MATCH_MANAGER);
        }

        store.setStoreName(request.getStoreName());
        store.setAddress(request.getAddress());
        store.setDescription(request.getDescription());

        return StoreDto.fromEntity(this.storeRepository.save(store));
    }

    // 식당 정보 조회
    @Override
    public DetailStore detailStore(String storeName) {
        Store store = checkStoreName(storeName);
        return DetailStore.from(store);
    }

    // 등록된 식당인지 확인
    private Store checkStoreName(String storeName) {
        return this.storeRepository.findByStoreName(storeName)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
    }
}
