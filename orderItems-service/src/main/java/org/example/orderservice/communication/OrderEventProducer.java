package org.example.orderservice.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderEventProducer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderDeleteEvent(Long orderId) {
        kafkaTemplate.send("order-delete-event", ""+orderId);
    }

    public void sendOrderCreateEvent(SpecialOrderDto dto) throws JsonProcessingException {
        String jsonToString = new ObjectMapper().writeValueAsString(dto);
        kafkaTemplate.send("order-create-event", jsonToString);
    }
}
