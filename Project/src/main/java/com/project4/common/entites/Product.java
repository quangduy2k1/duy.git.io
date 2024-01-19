package com.project4.common.entites;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Product")
@Data
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer productId;
    @Column(length = 50, nullable = false, name = "product_name")
    private String productName;
    @Column(length = 200, nullable = false, name = "description")
    private String description;
    @Column(nullable = false, name = "weight")
    private Float weight;
    @Column(nullable = false, name = "price")
    private Float price;
    @Column(nullable = false, name = "height")
    private Float height;
    @Column(nullable = false, name = "lenght")
    private Float lenght;
    @Column(nullable = false, name = "speed")
    private Float speed;
    @Column(nullable = false, name = "images")
    private String images;
    @Column(nullable = false, name = "cylinder_capacity")
    private Float cylinder_capacity;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand style;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private Collection<OrderDetail> orderDetails;
}
