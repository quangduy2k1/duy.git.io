package com.project4.user.feed_back;

import com.project4.common.entites.FeedBack;
import com.project4.common.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackUserService {
    private final FeedbackRepository feedbackRepository;

    public void save(FeedBack feedBack) {
        feedbackRepository.save(feedBack);
    }

}
