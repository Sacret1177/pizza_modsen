package org.example.orderservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public Order dtoToEntity(OrderDto dto, Long id) {
        Order order = new Order();
        order.setId(id);
        order.setUserId(dto.getUserId());
        return order;
    }

    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(order.getUserId());
        return orderDto;
    }

    public List<OrderDto> mapList(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<OrderDto>();
        for (Order order : orders) {
            orderDtos.add(entityToDto(order));
        }
        return orderDtos;
    }
}
