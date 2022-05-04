package com.project4.admin.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand")
@RequiredArgsConstructor
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandId;
    @Column(length = 50, nullable = false, name = "brandName")
    private String brandName;
    @Column(nullable = false, name = "logo")
    private String logo;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "brandId")
    private List<Product> productList;
}
