package com.best.company.rabbitmq.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import com.best.company.constants.MqConstants;

@Component
public class RabbitMqBaseProducer implements MqConstants {

    @Autowired
    protected AmqpTemplate amqpTemplate;
    @Autowired
    protected ObjectMapper objectMapper;

    protected void sendToQueue(String payloadString, String queueName, String payload) {
        this.sendToQueue(payloadString, queueName, payload, 2);
    }

    protected void sendToQueue(String payloadString, String queueName, String payload, int delay) {
        Message jsonMessage = MessageBuilder
                .withBody(payloadString.getBytes())
                .andProperties(
                        MessagePropertiesBuilder
                                .newInstance()
                                .setContentType(MediaType.APPLICATION_JSON_VALUE)
                                .setHeader(X_DELAY, (1000 * delay))
                                .setHeader(TYPE_ID, payload)
                                .build()
                )
                .build();
        amqpTemplate.send(DELAY_EXCHANGE_NAME, queueName, jsonMessage);
    }
}
