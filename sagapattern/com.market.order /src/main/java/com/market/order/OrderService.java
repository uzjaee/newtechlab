package com.market.order;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
  private Map<UUID, Order> orderstore = new HashMap<>();
  private final  RabbitTemplate rabbitTemplate;
  @Value("${message.queue.product}")
  private String productQueue;

  public Order createOrder(OrderEndpoint.OrderRequestDto orderRequestDto) {
    Order order = orderRequestDto.toOrder();
    orderstore.put(order.getOrderID(),order);

    // rabbitmq를 통한 메세지 전달
    DeliveryMessage deliveryMessage = orderRequestDto.toDeliveryMessage(order.getOrderID());
    rabbitTemplate.convertAndSend(productQueue, deliveryMessage);


    return order;
  }
  public Order getOrder(UUID orderId) {
    return orderstore.get(orderId);
  }

  public void rollbackOrder(DeliveryMessage deliveryMessage) {
    Order order = orderstore.get(deliveryMessage.getOrderId());
    order.cancelOrder(deliveryMessage.getErrorType());
  }

}
