package com.zerobase.restaurant.partner.repository;

import com.zerobase.restaurant.partner.domain.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    boolean existsByUsername(String username);
    Optional<Partner> findByUsername(String username);
}
