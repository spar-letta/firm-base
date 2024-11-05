package com.farm.base.notification_service.service;

import com.farm.base.notification_service.dto.MessageDto;
import com.farm.base.notification_service.model.MessageDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface MessageDetailService {
    Page<MessageDetail> fetchAllMessages(Long nationalId, String searchParam, String voucherNumber, LocalDate expiryDate, Pageable pageable);

    void saveMessageDeatils(MessageDto messageDto);
}
