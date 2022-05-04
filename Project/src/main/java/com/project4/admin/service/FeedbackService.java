package com.project4.admin.service;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.FeedBack;
import com.project4.admin.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public void save(FeedBack feedBack) {
        feedbackRepository.save(feedBack);
    }

    public List<FeedBack> listAll() {
        return feedbackRepository.findAll();
    }

    public FeedBack get(Integer id) throws ModelException {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new ModelException("could not find any Feedback with Id" + id));
    }

    public void delete(Integer id) {
        feedbackRepository.deleteById(id);
    }

}
