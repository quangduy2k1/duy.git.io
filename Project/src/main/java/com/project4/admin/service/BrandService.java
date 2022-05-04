package com.project4.admin.service;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Brand;
import com.project4.admin.models.Category;
import com.project4.admin.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private BrandRepository rep;

    public List<Brand> listAll() {
        return rep.findAll();
    }

    public void save(Brand style) {
        rep.save(style);
    }

    public Brand get(Integer id) throws ModelException {
        Optional<Brand> result = rep.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ModelException("could not find any Brand with Id" + id);
    }

    public void delete(Integer id) {
        rep.deleteById(id);
    }

    public Page<Brand> findNameB(int pageNumber, String brandName) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 3);
        if (brandName != null) {
            return rep.findAllByNameBrand(brandName, pageable);
        }
        return rep.findAll(pageable);

    }
}
