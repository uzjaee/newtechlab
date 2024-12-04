package com.market.order;

import java.util.UUID;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderEndpoint {
  private final OrderService orderService;

  @RabbitListener(queues = "${message.queue.err.order}")
  public void errOrder(DeliveryMessage deliveryMessage){
    log.info("Error Receive");
    orderService.rollbackOrder(deliveryMessage);
  }
  @GetMapping("/order/{orderId}")
  public ResponseEntity<Order> getOrder(@PathVariable UUID orderId) {
    Order order = orderService.getOrder(orderId);
    return ResponseEntity.ok(order);
  }

  @PostMapping("/order")
  public ResponseEntity<Order> Order(@RequestBody OrderRequestDto orderRequestDto) {
    Order order = orderService.createOrder(orderRequestDto);
    return ResponseEntity.ok(order);

  }

  @Data
  public static class OrderRequestDto {
    private String userId;
    private Integer productId;
    private Integer productQuantity;
    private Integer payAmount;

    public Order toOrder() {
      return Order.builder()
          .orderID(UUID.randomUUID())
          .userId(userId)
          .orderStatus("RECEIPT")
          .build();
    }

    public DeliveryMessage toDeliveryMessage(UUID orderId) {
      return DeliveryMessage.builder()
          .orderId(orderId)
          .productID(productId)
          .productQuantity(productQuantity)
          .payAmount(payAmount)
          .build();
    }

  }


}
