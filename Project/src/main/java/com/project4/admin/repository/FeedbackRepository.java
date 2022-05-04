package com.project4.admin.repository;

import com.project4.admin.models.FeedBack;
import com.project4.admin.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedBack,Integer> {

}
