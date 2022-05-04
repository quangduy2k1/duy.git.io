package com.project4.admin.repository;

import com.project4.admin.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT p FROM Category p where p.CategoryName like %?1%")
    public Page<Category> findAllByName(String categoryname, Pageable pageable);
}
