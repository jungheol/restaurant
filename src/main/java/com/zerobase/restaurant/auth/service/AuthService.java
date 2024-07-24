package com.zerobase.restaurant.auth.service;

import com.zerobase.restaurant.auth.dto.Login;
import com.zerobase.restaurant.auth.type.MemberType;
import com.zerobase.restaurant.common.exception.CustomException;
import com.zerobase.restaurant.partner.domain.Partner;
import com.zerobase.restaurant.partner.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.zerobase.restaurant.common.type.ErrorCode.PARTNER_NOT_FOUND;
import static com.zerobase.restaurant.common.type.ErrorCode.PASSWORD_NOT_MATCHED;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final PartnerRepository partnerRepository;
    private final PasswordEncoder passwordEncoder;

    public Partner authenticatePartner(Login login) {
        Partner partner = checkPartnerName(login.getUsername());

        if (!this.passwordEncoder.matches(login.getPassword(), partner.getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCHED);
        }

        return partner;
    }

    private Partner checkPartnerName(String username) {
        return this.partnerRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(PARTNER_NOT_FOUND));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws CustomException {
        if (this.partnerRepository.existsByUsername(username)) {
            Partner partner = checkPartnerName(username);

            return createUserDetail(partner.getUsername(),
                    partner.getPassword(), MemberType.PARTNER);
        }

        throw new CustomException(PARTNER_NOT_FOUND);
    }

    private UserDetails createUserDetail(String username, String password, MemberType memberType) {
        return User.withUsername(username)
                .password(this.passwordEncoder.encode(password))
                .roles(String.valueOf(memberType))
                .build();
    }
}
