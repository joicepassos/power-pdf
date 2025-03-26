package com.jfp.files.pdf.receiver;

import com.jfp.files.pdf.dto.PdfResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PdfResponseReceiver {

  @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
  public void receiveResponse(PdfResponseMessage message) {
    try {
      log.info("Response with message {} received", message.messageId());

      String outputPath = message.link();
      log.info("PDF merged successfully at {}", outputPath);

    } catch (Exception e) {
      log.error("Erro ao processar resposta de merge", e);
    }
  }
}
