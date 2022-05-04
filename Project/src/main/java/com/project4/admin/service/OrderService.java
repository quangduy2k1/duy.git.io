package com.project4.admin.service;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Admin;
import com.project4.admin.models.Order;
import com.project4.admin.models.Product;
import com.project4.admin.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderService {
    private Map<Product, Integer> products = new HashMap<>();
    @Autowired
    private OrderRepository repository;

    public void save(Order order) {
        repository.save(order);
    }

    public List<Order> listAll() {
        return repository.findAll();
    }


}

