package com.project4.admin.feed_back;

import com.project4.common.exception.ModelException;
import com.project4.common.entites.FeedBack;
import com.project4.common.repository.FeedbackRepository;
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
