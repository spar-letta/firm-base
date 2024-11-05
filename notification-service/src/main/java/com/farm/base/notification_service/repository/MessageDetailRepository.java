package com.farm.base.notification_service.repository;

import com.farm.base.notification_service.model.MessageDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDetailRepository extends JpaRepository<MessageDetail, Long> {
}
