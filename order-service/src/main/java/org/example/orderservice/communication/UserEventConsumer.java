package org.example.orderservice.communication;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.orderservice.Order;
import org.example.orderservice.OrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserEventConsumer {
    private final OrderService orderService;

    public UserEventConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "user-delete-event", groupId = "my-consumer-group")
    public void consumeUserDeleteEvent(ConsumerRecord<String, String> record){
        Long userId = Long.parseLong(record.value());
        List<Order> orders = new ArrayList<>();
        orders.addAll(orderService.findAll());
        orders.forEach(order -> {
            if(order.getUserId().equals(userId)){
                orderService.deleteOrder(order.getId());
            }
        });
    }
}
