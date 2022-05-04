package com.project4.admin.service;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Category;
import com.project4.admin.models.Product;
import com.project4.admin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository rep;

    public List<Product> listAll() {
        return rep.findAll();
    }

    public void save(Product product) {
        rep.save(product);
    }

    public Product get(Integer id) throws ModelException {
        Optional<Product> result = rep.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ModelException("could not find any Product with Id" + id);
    }

    public void delete(Integer id) {
        rep.deleteById(id);
    }

    public Page<Product> findNameProduct(int pageNumber, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        if (keyword != null) {
            return rep.findAllByNameProduct(pageable, keyword);
        }
        return rep.findAll(pageable);

    }

    public Page<Product> findNameProducts(int pageNumber, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 12);
        if (keyword != null) {
            return rep.findAllByNameProduct(pageable, keyword);
        }
        return rep.findAll(pageable);

    }

    public Page<Product> findNamep(String categoryname, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return rep.findAllByNamep(categoryname, pageable);
    }

    public Page<Product> findNameP(String categoryname, int page) {
        Pageable pageable = PageRequest.of(page - 1, 8);
        return rep.findAllByNamep(categoryname, pageable);
    }

    public Page<Product> findNameb(String brandName, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return rep.findAllByNameb(brandName, pageable);
    }

    public Page<Product> findNameB(String brandName, int page) {
        Pageable pageable = PageRequest.of(page - 1, 8);
        return rep.findAllByNameb(brandName, pageable);
    }

    public Product findById(Integer id) {
        Product p = rep.findById(id).orElse(null);
        return p;
    }

}
