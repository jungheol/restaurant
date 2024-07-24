package com.zerobase.restaurant.store.service;

import com.zerobase.restaurant.common.exception.CustomException;
import com.zerobase.restaurant.partner.domain.Partner;
import com.zerobase.restaurant.partner.repository.PartnerRepository;
import com.zerobase.restaurant.store.domain.Store;
import com.zerobase.restaurant.store.dto.RegisterStore;
import com.zerobase.restaurant.store.dto.StoreDto;
import com.zerobase.restaurant.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.restaurant.common.type.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final PartnerRepository partnerRepository;
    @Override
    @Transactional
    public StoreDto registerStore(RegisterStore.Request request) {
        log.info("매장 등록 시작");

        Partner partner = this.partnerRepository.findById(request.getPartnerId())
                        .orElseThrow(() -> new CustomException(PARTNER_NOT_FOUND));

        if (this.storeRepository.existsByStoreName(request.getStoreName())) {
            throw new CustomException(ALREADY_EXISTED_RESTAURANT);
        }

        log.info("매장 등록 완료");
        return StoreDto.fromEntity(this.storeRepository.save(Store.builder()
                .partner(partner)
                .storeName(request.getStoreName())
                .address(request.getAddress())
                .description(request.getDescription())
                .build()));
    }

    @Override
    @Transactional
    public void deleteStore(Long storeId, Long partnerId) {
        Store store = this.storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

        if (!store.getPartner().getId().equals(partnerId)) {
            throw new CustomException(STORE_NOT_MATCH_MANAGER);
        }

        log.info("매장 정보 삭제 완료");
        this.storeRepository.delete(store);
    }
}
