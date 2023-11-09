package com.best.company.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.best.company.dto.payload.CompanyTINPayload;

import java.util.List;

@Component
@Slf4j
public class RabbitMqCompanyProducer extends RabbitMqBaseProducer {

    public void syncTins(List<String> tinPayload, int delay) {
        tinPayload.forEach(tin -> {
            try {
                sendToQueue(objectMapper.writeValueAsString(new CompanyTINPayload(tin)), COMPANY_TIN_SYNC_QUEUE, COMPANY_TIN_SYNC_PAYLOAD, delay);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
        });
    }
}
