package org.example.productservice.communication;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.productservice.Product;
import org.example.productservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryEventConsumer {
    @Autowired
    private ProductService productService;

    @KafkaListener(topics = "category-delete-event", groupId = "my-consumer-group")
    public void consumeCategoryDeleteEvent(ConsumerRecord<String, String> record) {
        Long categoryId = Long.parseLong(record.value());
        List<Product> prods = new ArrayList<>();
        prods.addAll(productService.getAllProducts());
        prods.forEach(product -> {
            if(product.getCategoryId().equals(categoryId)) {
                productService.deleteProduct(product.getId());
            }
        });
    }
}
