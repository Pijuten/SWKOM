package at.fhtw.ocrservice.services;

import at.fhtw.ocrservice.services.ocr.MinoIOFileDownloader;
import at.fhtw.ocrservice.services.ocr.OcrWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.File;
@Slf4j
@Component
public class RabbitReceiverService {
    private final MinoIOFileDownloader minoIOFileDownloader;
    private final OcrWorker ocrWorker;

    public RabbitReceiverService(MinoIOFileDownloader minoIOFileDownloader, OcrWorker ocrWorker) {
        this.minoIOFileDownloader = minoIOFileDownloader;
        this.ocrWorker = ocrWorker;
    }

    @RabbitListener(queues = "ocr_queue")
    public void receiveResultMessage(String documentName) {
        try {
            File file = minoIOFileDownloader.download(documentName);
            ocrWorker.performOcr(file, documentName);
            log.info("Successfully received Document from OCR Queue!", documentName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to receive result Message from OCR!", e);
        }
    }
}