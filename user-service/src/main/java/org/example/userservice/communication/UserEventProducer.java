package org.example.userservice.communication;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserEventProducer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserDeleteEvent(Long userId) {
        kafkaTemplate.send("user-delete-event", ""+userId);
    }
}
