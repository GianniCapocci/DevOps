package gr.hua.dev_ops.service;

import gr.hua.dev_ops.dto.ReviewDTO;
import gr.hua.dev_ops.entity.Review;
import gr.hua.dev_ops.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    void addReview(ReviewDTO review, User currentUser);
}
