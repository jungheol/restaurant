package com.zerobase.restaurant.store.domain;

import com.zerobase.restaurant.common.domain.BaseTimeEntity;
import com.zerobase.restaurant.partner.domain.Partner;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="partner_id")
    private Partner partner;

    private String storeName;

    private String address;

    private String description;

}
