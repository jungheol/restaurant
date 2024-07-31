package com.zerobase.restaurant.partner.service;

import com.zerobase.restaurant.partner.dto.PartnerDto;
import com.zerobase.restaurant.partner.dto.RegisterPartner;

public interface PartnerService {

    PartnerDto register(RegisterPartner user);

}
