package com.project4.admin.order;

import com.project4.common.exception.ModelException;
import com.project4.common.entites.OrderDetail;
import com.project4.common.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> listAll() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail get(Integer id) throws ModelException {
        return orderDetailRepository.findById(id).orElseThrow(() -> new ModelException("could not find any OrderDetails with Id" + id));
    }

    public Page<OrderDetail> findIds(Integer id, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return orderDetailRepository.finId(id, pageable);
    }
}