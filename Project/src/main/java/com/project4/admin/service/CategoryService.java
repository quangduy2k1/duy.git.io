package com.project4.admin.service;

import com.project4.admin.models.Category;
import com.project4.admin.exception.ModelException;
import com.project4.admin.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired private CategoryRepository repo;
    public List<Category> listAll(){
        return repo.findAll();
    }
    public void save(Category category) {
        repo.save(category);
    }
    public Category get(Integer id) throws ModelException {
        Optional<Category> result=repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new ModelException("could not find any Category with Id" +id);
    }
    public void delete(Integer id){
        repo.deleteById(id);
    }
    public Page<Category> findName(int pageNumber,String categoryname) {
        Pageable pageable= PageRequest.of(pageNumber - 1,2);
        if(categoryname!=null){
            return repo.findAllByName(categoryname,pageable);
        }
        return repo.findAll(pageable);

    }


}
