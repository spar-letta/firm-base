package com.firm_base.farmer_service.service;

import com.firm_base.farmer_service.model.Voucher;
import com.firm_base.farmer_service.model.request.VoucherDto;

public interface VoucherService {
    Voucher createVoucher(VoucherDto voucherDto);
}
