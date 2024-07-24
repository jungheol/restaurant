package com.zerobase.restaurant.store.repository;

import com.zerobase.restaurant.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
