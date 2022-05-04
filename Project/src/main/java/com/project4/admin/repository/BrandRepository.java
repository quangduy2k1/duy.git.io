package com.project4.admin.repository;

import com.project4.admin.models.Brand;
import com.project4.admin.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    @Query("SELECT p FROM Brand p where p.brandName like %?1%")
    public Page<Brand> findAllByNameBrand(String brandName, Pageable pageable);
}
