package com.project4.common.repository;

import com.project4.common.entites.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    @Query("SELECT p FROM Brand p where p.brandName like %:brandName%")
    Page<Brand> findAllByNameBrand(String brandName, Pageable pageable);
}
