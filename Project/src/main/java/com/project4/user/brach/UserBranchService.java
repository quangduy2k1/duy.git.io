package com.project4.user.brach;

import com.project4.common.entites.Brand;
import com.project4.common.exception.ModelException;
import com.project4.common.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBranchService {
    private final BrandRepository brandRepository;
    public List<Brand> listAll() {
        return brandRepository.findAll();
    }

    public void save(Brand style) {
        brandRepository.save(style);
    }

    public Brand get(Integer id) throws ModelException {
        return brandRepository.findById(id).orElseThrow(() -> new ModelException("could not find any Brand with Id" + id));
    }

    public void delete(Integer id) {
        brandRepository.deleteById(id);
    }

    public Page<Brand> findNameB(int pageNumber, String brandName) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 3);
        if (brandName != null) {
            return brandRepository.findAllByNameBrand(brandName, pageable);
        }
        return brandRepository.findAll(pageable);

    }
}
