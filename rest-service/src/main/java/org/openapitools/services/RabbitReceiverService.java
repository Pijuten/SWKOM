package org.openapitools.services;

import lombok.extern.slf4j.Slf4j;
import org.openapitools.repositories.jpa.DocumentContentJPARepository;
import org.openapitools.services.dto.DocumentContentDto;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
public class RabbitReceiverService {
    private final DocumentContentJPARepository documentContentJPARepository;

    public RabbitReceiverService(DocumentContentJPARepository documentContentJPARepository) {
        this.documentContentJPARepository = documentContentJPARepository;
    }

    @RabbitListener(queues = "result_queue")
    public void receiveResultMessage(String message) {
        try {
            String[] result = spiltAround_(message);

            log.info("Received message: {}", message);
            documentContentJPARepository.save(new DocumentContentDto(UUID.fromString(result[0]), result[1]));
            log.info("Document saved successfully!");

        } catch (Exception e) {
          // Log the error and handle appropriately
            log.error("Error processing message: {} - Error: {}", message, e.getMessage(),e);
            throw new AmqpRejectAndDontRequeueException("Failed to process message", e);
        }
    }


    public String[] spiltAround_(String message) {
        try{
        return message.split("_");
    }
        catch(Exception e){
        log.error("Error splitting message: {} - Error: {}", message, e.getMessage(),e);
        throw new AmqpRejectAndDontRequeueException("Invalid message format for splitting", e);
        }
    }
}