package org.openapitools.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiverService {

    @RabbitListener(queues = "result_queue")
    public String receiveResultMessage(String message) {
        // Process result queue message
        return message;
    }
}