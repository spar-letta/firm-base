package com.farm.base.notification_service.utils;

import com.farm.base.notification_service.config.EventConfig;
import com.farm.base.notification_service.dto.MessageDto;
import com.farm.base.notification_service.service.MessageDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class handleNotificationEvents {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final MessageDetailService messageDetailService;

    @RabbitListener(queues = EventConfig.MESSAGE_QUEUE)
    public void handlePostReportEvent(MessageDto messageDto) {
        try {
            log.info("Message received ==== {}", messageDto);
            messageDetailService.saveMessageDeatils(messageDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
