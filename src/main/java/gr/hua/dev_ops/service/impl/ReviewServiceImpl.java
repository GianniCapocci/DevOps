package gr.hua.dev_ops.service.impl;

import gr.hua.dev_ops.dto.ReviewDTO;
import gr.hua.dev_ops.entity.*;
import gr.hua.dev_ops.repository.*;
import gr.hua.dev_ops.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ListingRepository listingRepository;

    @Override
    @Transactional
    public void addReview(ReviewDTO reviewDto, User currentUser) {
        Listing listing = listingRepository.findById(reviewDto.getListingId())
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        Client client = clientRepository.findById(currentUser.getId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Review review = new Review();
        review.setListing(listing);
        review.setClient(client);
        review.setRating(reviewDto.getRating());
        review.setContent(reviewDto.getContent());

        reviewRepository.save(review);
    }
}
