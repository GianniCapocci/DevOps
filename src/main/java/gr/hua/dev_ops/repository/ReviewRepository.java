package gr.hua.dev_ops.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.hua.dev_ops.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByListingId(Long listingId);
}
