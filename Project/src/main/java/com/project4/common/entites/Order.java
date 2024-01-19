package com.project4.common.entites;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "listOrder")
@Data
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Column(length = 50, nullable = false, name = "orderdate")
    private Date orderdate;
    @Column(nullable = false, name = "status")
    private String status;
    @Column(nullable = false, name = "total")
    private Float total;
    @Column(nullable = false, name = "address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Collection<OrderDetail> orderDetails;
}
