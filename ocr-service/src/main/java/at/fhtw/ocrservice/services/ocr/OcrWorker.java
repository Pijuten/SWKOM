package at.fhtw.ocrservice.services.ocr;

import at.fhtw.ocrservice.repositories.elasticsearch.DocumentElasticsearchRepository;
import at.fhtw.ocrservice.services.RabbitMQSenderService;
import at.fhtw.ocrservice.services.dto.DocumentContentDto;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

@Service
public class OcrWorker {
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
            // Explicitly set image format or use ImageIO to preprocess
            BufferedImage image = ImageIO.read(file);
            String content = tesseract.doOCR(image);
            rabbitMQSenderService.sendToOcrQueue(id+"_"+content);
            documentElasticsearchRepository.save(new DocumentContentDto(UUID.fromString(id),content));
            return content;
        } catch (Exception e) {
            return null;
        }
    }
}
