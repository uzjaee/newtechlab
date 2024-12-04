package com.market.payment;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryMessage {
  private UUID orderId;
  private UUID paymentId;

  private String userID;

  private Integer productID;
  private Integer productQuantity;

  private Integer payAmount;

  private String errorType;


}
