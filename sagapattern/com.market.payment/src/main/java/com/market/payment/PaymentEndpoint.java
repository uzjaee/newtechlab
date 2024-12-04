package com.market.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentEndpoint {
  private final PaymentService paymentService;

  @RabbitListener(queues = "${message.queue.payment}")
  public void receiveMessage(DeliveryMessage deliveryMessage) {
    log.info("Received Message: {}", deliveryMessage);
    paymentService.createPayment(deliveryMessage);
  }

}
