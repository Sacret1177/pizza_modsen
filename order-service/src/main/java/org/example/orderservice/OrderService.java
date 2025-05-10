package org.example.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import org.example.orderservice.communication.OrderEventProducer;
import org.example.orderservice.communication.SpecialOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderEventProducer orderEventProducer;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Page<Order> findAll(Pageable pageable) {
        return  orderRepository.findAll(pageable);
    }

    public  Order save(OrderDto dto) {
        Order order = new Order();
        order.setUserId(dto.getUserId());
        Order orderRepo = orderRepository.save(order);
        SpecialOrderDto sdto = new SpecialOrderDto();
        sdto.setorderId(orderRepo.getId());
        sdto.setproductId(6L);
        sdto.setAmount(10);
        try {
            orderEventProducer.sendOrderCreateEvent(sdto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return orderRepo;
    }

    public Order updateOrder(Order order){
        if(!orderRepository.existsById(order.getId())){
            throw new EntityNotFoundException("Order Not Found");
        }
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        if(!orderRepository.existsById(id)){
            throw new EntityNotFoundException("Order Not Found");
        }
        orderRepository.deleteById(id);
        orderEventProducer.sendOrderDeleteEvent(id);
    }
}
