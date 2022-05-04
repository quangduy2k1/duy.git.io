package com.project4.admin.service;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.FeedBack;
import com.project4.admin.models.Product;
import com.project4.admin.models.User;
import com.project4.admin.repository.FeedbackRepository;
import com.project4.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository repo;

    public void save(FeedBack feedBack) {
        repo.save(feedBack);
    }

    public List<FeedBack> listAll() {
        return repo.findAll();
    }

    public FeedBack get(Integer id) throws ModelException {
        Optional<FeedBack> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ModelException("could not find any Feedback with Id" + id);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
