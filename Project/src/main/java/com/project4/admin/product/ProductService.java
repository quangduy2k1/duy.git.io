package com.project4.admin.product;

import com.project4.common.exception.ModelException;
import com.project4.common.entites.Product;
import com.project4.common.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product get(Integer id) throws ModelException {
        return productRepository.findById(id).orElseThrow(() -> new ModelException("could not find any Product with Id" + id));
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findNameProduct(int pageNumber, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        if (keyword != null) {
            return productRepository.findAllByNameProduct(pageable, keyword);
        }
        return productRepository.findAll(pageable);

    }

    public Page<Product> findNameProducts(int pageNumber, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 12);
        if (keyword != null) {
            return productRepository.findAllByNameProduct(pageable, keyword);
        }
        return productRepository.findAll(pageable);

    }

    public Page<Product> findNamep(String categoryname, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return productRepository.findAllByNamep(categoryname, pageable);
    }

    public Page<Product> findNameP(String categoryname, int page) {
        Pageable pageable = PageRequest.of(page - 1, 8);
        return productRepository.findAllByNamep(categoryname, pageable);
    }

    public Page<Product> findNameb(String brandName, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return productRepository.findAllByNameb(brandName, pageable);
    }

    public Page<Product> findNameB(String brandName, int page) {
        Pageable pageable = PageRequest.of(page - 1, 8);
        return productRepository.findAllByNameb(brandName, pageable);
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ModelException("could not find any Product with Id" + id));
    }

}
