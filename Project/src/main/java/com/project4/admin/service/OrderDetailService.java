package com.project4.admin.service;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.OrderDetail;
import com.project4.admin.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    @Autowired
    public OrderDetailRepository repository;

    public void save(OrderDetail orderDetail) {
        repository.save(orderDetail);
    }

    public List<OrderDetail> listAll() {
        return repository.findAll();
    }

    public OrderDetail get(Integer id) throws ModelException {
        Optional<OrderDetail> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ModelException("could not find any OrderDetails with Id" + id);
    }

    public Page<OrderDetail> findIds(Integer id, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return repository.finId(id, pageable);
    }
}