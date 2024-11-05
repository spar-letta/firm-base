package com.firm_base.farmer_service.repository;

import com.firm_base.farmer_service.model.VoucherItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherItemRepository extends JpaRepository<VoucherItem, Long> {
}
