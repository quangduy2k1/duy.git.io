package com.project4.admin.order;

import com.project4.common.entites.Order;
import com.project4.common.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> listAll() {
        return orderRepository.findAll();
    }


}

