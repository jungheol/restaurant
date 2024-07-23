package com.zerobase.restaurant.partner.dto;

import com.zerobase.restaurant.auth.type.MemberType;
import com.zerobase.restaurant.partner.domain.Partner;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDto {
    private Long id;
    private String username;
    private MemberType memberType;
    private String password;

    public static PartnerDto fromEntity(Partner partner) {
        return PartnerDto.builder()
                .id(partner.getId())
                .username(partner.getUsername())
                .memberType(partner.getMemberType())
                .password(partner.getPassword())
                .build();
    }

}
