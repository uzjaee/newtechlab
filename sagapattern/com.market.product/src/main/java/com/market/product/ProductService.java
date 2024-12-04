package com.market.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductService {
  @Value("${message.queue.payment}")
  private String paymentQueue;
  @Value("${message.queue.err.order}")
  private String errOrderQueue;

  private final RabbitTemplate rabbitTemplate;

  public  void reduceProduct(DeliveryMessage deliveryMessage) {
    Integer productId = deliveryMessage.getProductID();
    Integer productQuantity = deliveryMessage.getProductQuantity();

    if(productId !=1 || productQuantity >1) {
      this.rollbackProduct(deliveryMessage);
      return;
    }
    rabbitTemplate.convertAndSend(paymentQueue,deliveryMessage);
  }

  public void rollbackProduct(DeliveryMessage deliveryMessage) {
    log.info("Product Rollback");
    if(!StringUtils.hasText(deliveryMessage.getErrorType())){
      deliveryMessage.setErrorType("Product Error");
    }
    rabbitTemplate.convertAndSend(errOrderQueue,deliveryMessage);

  }

}
