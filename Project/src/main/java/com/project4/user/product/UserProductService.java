package com.project4.user.product;

import com.project4.common.entites.Product;
import com.project4.common.exception.ModelException;
import com.project4.common.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {
    private final ProductRepository productRepository;
    public List<Product> listAll() {
        return productRepository.findAll();
    }
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ModelException("could not find any Product with Id" + id));
    }
}
