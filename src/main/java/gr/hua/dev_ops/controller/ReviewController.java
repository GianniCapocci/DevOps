package gr.hua.dev_ops.controller;

import gr.hua.dev_ops.entity.Review;
import gr.hua.dev_ops.entity.User;
import gr.hua.dev_ops.repository.ReviewRepository;
import gr.hua.dev_ops.service.ReviewService;
import gr.hua.dev_ops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import gr.hua.dev_ops.dto.ReviewDTO;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/add")
    public ResponseEntity<Void> submitReview(@RequestBody ReviewDTO reviewDto, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.findByEmail(userDetails.getUsername());
        reviewService.addReview(reviewDto, currentUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listing/{listingId}")
    public ResponseEntity<List<Review>> getReviewsByListingId(@PathVariable Long listingId) {
        List<Review> reviews = reviewRepository.findByListingId(listingId);
        return ResponseEntity.ok(reviews);
    }
}
