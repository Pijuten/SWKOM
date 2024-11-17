package org.openapitools.services;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSenderService {
    private final AmqpTemplate amqpTemplate;

    public RabbitMQSenderService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendToOcrQueue(String message) {
        amqpTemplate.convertAndSend("ocr_queue", message);
    }
}
