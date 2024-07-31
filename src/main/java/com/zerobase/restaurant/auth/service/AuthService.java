package com.zerobase.restaurant.auth.service;

import com.zerobase.restaurant.auth.dto.Login;
import com.zerobase.restaurant.auth.type.MemberType;
import com.zerobase.restaurant.common.exception.CustomException;
import com.zerobase.restaurant.customer.domain.Customer;
import com.zerobase.restaurant.customer.repository.CustomerRepository;
import com.zerobase.restaurant.partner.domain.Partner;
import com.zerobase.restaurant.partner.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.restaurant.auth.type.MemberType.CUSTOMER;
import static com.zerobase.restaurant.auth.type.MemberType.PARTNER;
import static com.zerobase.restaurant.common.type.ErrorCode.*;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final PartnerRepository partnerRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    // 파트너 로그인 시 패스워드 확인
    public Partner authenticatePartner(Login login) {
        Partner partner = checkPartnerEmail(login.getEmail());

        if (!this.passwordEncoder.matches(login.getPassword(), partner.getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCHED);
        }

        return partner;
    }

    // 유저 로그인 시 패스워드 확인
    public Customer authenticateCustomer(Login login) {
        Customer customer = checkCustomerEmail(login.getEmail());

        if (!this.passwordEncoder.matches(login.getPassword(), customer.getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCHED);
        }

        return customer;
    }

    // 파트너 및 유저의 email을 이용하여 일치하는 파트너 및 유저의 정보 반환
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (this.partnerRepository.existsByEmail(email)) {
            Partner partner = checkPartnerEmail(email);

            return createUserDetail(partner.getEmail(),
                    partner.getPassword(), PARTNER);
        } else if (this.customerRepository.existsByEmail(email)) {
            Customer customer = checkCustomerEmail(email);

            return createUserDetail(customer.getEmail(),
                    customer.getPassword(), CUSTOMER);
        }

        throw new UsernameNotFoundException("USER not found with email: " + email);
    }

    // 해당 이메일로 가입된 파트너 있는지 확인
    private Partner checkPartnerEmail(String email) {
        return this.partnerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(PARTNER_NOT_FOUND));
    }

    // 해당 이메일로 가입된 유저 있는지 확인
    private Customer checkCustomerEmail(String email) {
        return this.customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));
    }

    private UserDetails createUserDetail(String username, String password, MemberType memberType) {
        return User.withUsername(username)
                .password(this.passwordEncoder.encode(password))
                .roles(String.valueOf(memberType))
                .build();
    }
}
