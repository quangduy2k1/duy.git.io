package com.project4.admin.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer productId;
    @Column(length = 50,nullable = false, name = "product_name")
    private String productName;
    @Column(length = 200,nullable = false, name = "description")
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
    public Product() {};
    public Product(String productName, Float price, Category category, String descripton, Float weight, Brand style,
                   Float height,Float lenght,Float speed,Float cylinder_capacity,String images) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.description =descripton;
        this.weight=weight;
        this.style=style;
        this.height=height;
        this.lenght=lenght;
        this.speed=speed;
        this.cylinder_capacity=cylinder_capacity;
        this.images=images;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getStyle() {
        return style;
    }

    public void setStyle(Brand style) {
        this.style = style;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getLenght() {
        return lenght;
    }

    public void setLenght(Float lenght) {
        this.lenght = lenght;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Float getCylinder_capacity() {
        return cylinder_capacity;
    }

    public void setCylinder_capacity(Float cylinder_capacity) {
        this.cylinder_capacity = cylinder_capacity;
    }

    public Collection<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Collection<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
