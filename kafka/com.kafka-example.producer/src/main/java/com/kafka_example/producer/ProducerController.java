package com.kafka_example.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProducerController {
  private final ProducerService producerService;



  @GetMapping("/send")
  public String sendMessage(@RequestParam("topic") String topic,
                            @RequestParam("key") String key,
                            @RequestParam("message") String message) {
    producerService.sendMessage(topic, key, message);
    return "Message sent to topic: ";
  }

}
