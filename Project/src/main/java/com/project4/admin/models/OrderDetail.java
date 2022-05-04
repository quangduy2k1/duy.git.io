package com.project4.admin.models;

import javax.persistence.*;

@Entity
@Table(name="OrderDetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oderDetailId;
    @Column(length = 50,nullable = false, name = "price")
    private String price;
    @Column(length = 50,nullable = false, name = "qty")
    private String qty;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    public OrderDetail() {
        super();
    }

    public OrderDetail(Integer oderDetailId, String price, String qty, Product product, Order order) {
        this.oderDetailId=oderDetailId;
        this.price=price;
        this.qty=qty;
        this.product=product;
        this.order=order;
    }


    public Integer getOderDetailId() {
        return oderDetailId;
    }

    public OrderDetail setOderDetailId(Integer oderDetailId) {
        this.oderDetailId = oderDetailId;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public OrderDetail setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getQty() {
        return qty;
    }

    public OrderDetail setQty(String qty) {
        this.qty = qty;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public OrderDetail setOrder(Order order) {
        this.order = order;
        return this;
    }


}
