package com.jfp.files.pdf.receiver;

import com.jfp.files.pdf.dto.PdfResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PdfResponseReceiver {

  @RabbitListener(
      bindings =
          @QueueBinding(
              value = @Queue(value = "pdf_response_queue", durable = "true"),
              exchange = @Exchange(value = "pdf_response_exchange"),
              key = "pdf.response.*"))
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
