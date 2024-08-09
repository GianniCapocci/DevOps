package gr.hua.dev_ops.repository;

import gr.hua.dev_ops.entity.Broker;
import gr.hua.dev_ops.entity.Client;
import gr.hua.dev_ops.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByClient(Client client);
    List<Review> findByListingId(Long listingId);
}
