package com.project4.admin.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@RequiredArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CategoryId;
    @Column(length = 50, nullable = false, name = "category_name")
    private String CategoryName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private List<Product> productList;
}
