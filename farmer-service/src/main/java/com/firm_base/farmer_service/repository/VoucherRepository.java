package com.firm_base.farmer_service.repository;

import com.firm_base.farmer_service.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
}
