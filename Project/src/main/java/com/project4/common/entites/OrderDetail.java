package com.project4.common.entites;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "OrderDetail")
@Data
@RequiredArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oderDetailId;
    @Column(length = 50, nullable = false, name = "price")
    private String price;
    @Column(length = 50, nullable = false, name = "qty")
    private String qty;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
}
