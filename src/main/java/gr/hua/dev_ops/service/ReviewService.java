package gr.hua.dev_ops.service;

import org.springframework.stereotype.Service;

import gr.hua.dev_ops.dto.ReviewDTO;
import gr.hua.dev_ops.entity.User;

@Service
public interface ReviewService {
    void addReview(ReviewDTO review, User currentUser);
}
