package at.fhtw.ocrservice.services;
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
            amqpTemplate.convertAndSend("result_queue", message);
            log.info("Successfully received Document from OCR Queue: {}", message);  // Corrected line
        }
        catch(Exception e){
           log.error("Error sending message to result: {} - Error: {}", message, e.getMessage());
            throw new RuntimeException("Failed to send message to result_queue: " + e.getMessage());
        }
    }
}