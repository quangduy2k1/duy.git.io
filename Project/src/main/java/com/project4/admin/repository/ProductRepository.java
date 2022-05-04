package com.project4.admin.repository;

import com.project4.admin.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p where "+"concat(p.productName,p.style.brandName,p.category.CategoryName,p.height,p.lenght,p.speed,p.price,p.cylinder_capacity,p.weight)"+ "like %?1%")
    public Page<Product> findAllByNameProduct(Pageable pageable,String keyword);
    @Query("SELECT p FROM Product p where p.category.CategoryName like ?1")
    public Page<Product> findAllByNamep(String categoryName,Pageable pageable);
    @Query("SELECT p FROM Product p where p.style.brandName like ?1")
    public Page<Product> findAllByNameb(String brandName,Pageable pageable);
    public Optional<Product> findById(Integer id);
}
