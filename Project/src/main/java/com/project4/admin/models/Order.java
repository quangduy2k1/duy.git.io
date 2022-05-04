package com.project4.admin.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="listOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Column(length = 50,nullable = false, name = "orderdate")
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
    @JoinColumn(name="orderId")
    private Collection<OrderDetail> orderDetails;
    public Order() {
    }

    public Order(Integer orderId,Date orderdate,String status,Float total,String address,User user) {
        this.orderId = orderId;
        this.orderdate=orderdate;
        this.status=status;
        this.total=total;
        this.address=address;
        this.user=user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Collection<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
