package com.project4.admin.repository;

import com.project4.admin.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT p FROM Category p where p.CategoryName like %:categoryName%")
    Page<Category> findAllByName(String categoryName, Pageable pageable);
}
