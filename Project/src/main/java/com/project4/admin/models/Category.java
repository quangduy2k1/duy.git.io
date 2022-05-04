package com.project4.admin.models;

import org.w3c.dom.Text;

import javax.persistence.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.List;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CategoryId;
    @Column(length = 50,nullable = false, name = "category_name")
    private String CategoryName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="categoryId")
    private List<Product> productList;
    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Integer categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

}
