package com.project4.admin.repository;

import com.project4.admin.models.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT p FROM OrderDetail p where p.order.orderId = :id")
    Page<OrderDetail> finId(Integer id, Pageable pageable);
}
