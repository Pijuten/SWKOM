package org.openapitools.services;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class RabbitMQSenderService {
    private final AmqpTemplate amqpTemplate;

    public RabbitMQSenderService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendToOcrQueue(String message) {
       try{
           log.info("Sending message to ocr_queue: {}", message);
           amqpTemplate.convertAndSend("ocr_queue", message);
           log.info("Message sent successfully to ocr_queue");
       }
       catch(Exception e){
           log.error("Error sending message to ocr_queue: {} - Error: {}", message,e.getMessage(), e);
          throw new RuntimeException("Failed to send message to ocr_queue: " + e.getMessage());
       }
    }
}
