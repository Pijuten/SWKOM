package org.openapitools.services;

import org.openapitools.repositories.jpa.DocumentContentJPARepository;
import org.openapitools.services.dto.DocumentContentDto;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
            documentContentJPARepository.save(new DocumentContentDto(UUID.fromString(result[0]), result[1]));
        } catch (Exception e) {
            // Log the error and handle appropriately
            System.err.println("Error processing message: " + message + ", Error: " + e.getMessage());
            throw new AmqpRejectAndDontRequeueException("Failed to process message", e);
        }
    }


    public String[] spiltAround_(String message) {
        return message.split("_");
    }
}