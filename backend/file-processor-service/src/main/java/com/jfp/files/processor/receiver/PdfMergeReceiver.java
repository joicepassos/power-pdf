package com.jfp.files.processor.receiver;

import com.jfp.files.processor.dto.PdfMergeMessage;
import com.jfp.files.processor.dto.PdfResponseMessage;
import com.jfp.files.processor.service.PdfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PdfMergeReceiver {

  private final PdfService pdfService;
  private final RabbitTemplate rabbitTemplate;

  @RabbitListener()
  public void receiveMessage(PdfMergeMessage message) {
    log.info("Message {} received - Paths to Merge: {}", message.messageId(), message.files());

    try {
      String outputPath = pdfService.mergePdfFiles(message.files(), message.fileName());

      PdfResponseMessage response =
          new PdfResponseMessage(message.messageId(), message.fileDocumentId(), outputPath);

      rabbitTemplate.convertAndSend("${spring.rabbitmq.exchange.name}", "pdf.response", response);

      log.info("Message {} processed - Output Path: {}", message.messageId(), outputPath);
    } catch (Exception e) {
      log.error("Error processing message {}", message.messageId(), e);
      throw new RuntimeException(e);
    }
  }
}
