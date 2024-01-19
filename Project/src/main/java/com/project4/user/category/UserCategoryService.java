package com.project4.user.category;

import com.project4.common.entites.Category;
import com.project4.common.exception.ModelException;
import com.project4.common.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category get(Integer id) throws ModelException {
        return categoryRepository.findById(id).orElseThrow(() -> new ModelException("could not find any Category with Id" + id));
    }

    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    public Page<Category> findName(int pageNumber, String categoryname) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 2);
        if (categoryname != null) {
            return categoryRepository.findAllByName(categoryname, pageable);
        }
        return categoryRepository.findAll(pageable);
    }
}
