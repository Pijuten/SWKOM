package at.fhtw.ocrservice.services.ocr;

import at.fhtw.ocrservice.repositories.elasticsearch.DocumentElasticsearchRepository;
import at.fhtw.ocrservice.services.RabbitMQSenderService;
import at.fhtw.ocrservice.services.dto.DocumentContentDto;
import net.sourceforge.tess4j.Tesseract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;


@Service
public class OcrWorker {
    private static final Logger logger = LoggerFactory.getLogger(OcrWorker.class); // Create a logger instance
    private final DocumentElasticsearchRepository documentElasticsearchRepository;
    private final RabbitMQSenderService rabbitMQSenderService;

    public OcrWorker(DocumentElasticsearchRepository documentElasticsearchRepository, RabbitMQSenderService rabbitMQSenderService) {
        this.documentElasticsearchRepository = documentElasticsearchRepository;
        this.rabbitMQSenderService = rabbitMQSenderService;
    }

    public String performOcr(File file, String id) {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("/app/tessdata_best/");
        try {
            logger.info("Starting OCR process for file: {}", file.getName());
            // Explicitly set image format or use ImageIO to preprocess
            BufferedImage image = ImageIO.read(file);
            String content = tesseract.doOCR(image);
            logger.info("OCR completed for file: {} with content size: {}", file.getName(), content.length());

            rabbitMQSenderService.sendToOcrQueue(id+"_"+content);
            documentElasticsearchRepository.save(new DocumentContentDto(UUID.fromString(id),content));
            return content;

        } catch (Exception e) {
            logger.error("Error during OCR processing for file: {} and id: {}", file.getName(), id, e);
            return null;
        }
    }
}
