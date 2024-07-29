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
    private String email;
    private String password;
    private String username;
    private MemberType memberType;

    public static PartnerDto fromEntity(Partner partner) {
        return PartnerDto.builder()
                .id(partner.getId())
                .email(partner.getEmail())
                .password(partner.getPassword())
                .username(partner.getUsername())
                .memberType(partner.getMemberType())
                .build();
    }

}
