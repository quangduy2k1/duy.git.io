package com.project4.admin.repository;

import com.project4.admin.models.OrderDetail;
import com.project4.admin.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    @Query("SELECT p FROM OrderDetail p where p.order.orderId like ?1")
    public Page<OrderDetail> finId(Integer id,Pageable pageable);
}
