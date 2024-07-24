package com.zerobase.restaurant.store.repository;

import com.zerobase.restaurant.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    boolean existsByStoreName(String name);
    Optional<Store> findByStoreName(String storeName);
}
