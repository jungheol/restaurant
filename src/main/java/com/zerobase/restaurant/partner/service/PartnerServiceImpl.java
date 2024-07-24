package com.zerobase.restaurant.partner.service;

import com.zerobase.restaurant.auth.type.MemberType;
import com.zerobase.restaurant.common.exception.CustomException;
import com.zerobase.restaurant.partner.domain.Partner;
import com.zerobase.restaurant.partner.dto.PartnerDto;
import com.zerobase.restaurant.partner.dto.RegisterPartner;
import com.zerobase.restaurant.partner.repository.PartnerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.zerobase.restaurant.common.type.ErrorCode.ALREADY_EXISTED_USER;
import static com.zerobase.restaurant.common.type.ErrorCode.PARTNER_NOT_FOUND;

@Service
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PasswordEncoder passwordEncoder;
    private final PartnerRepository partnerRepository;

    @Override
    @Transactional
    public PartnerDto register(RegisterPartner user) {
        boolean exists = this.partnerRepository.existsByUsername(user.getUsername());
        if (exists) {
            throw new CustomException(ALREADY_EXISTED_USER);
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Partner partner = this.partnerRepository.save(Partner.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .memberType(MemberType.PARTNER)
                .build());

        return PartnerDto.fromEntity(partner);
    }

    @Override
    public PartnerDto partnerDetail(Long userId) {
        Partner partner = this.partnerRepository.findById(userId)
                .orElseThrow(() -> new CustomException(PARTNER_NOT_FOUND));

        return PartnerDto.fromEntity(partner);
    }
}
