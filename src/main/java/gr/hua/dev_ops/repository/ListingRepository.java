package gr.hua.dev_ops.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.hua.dev_ops.entity.Listing;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByBrokerId(Long brokerId);
    List<Listing> findByBrokerIdIsNull();
    List<Listing> findByBrokerIdIsNotNull();
}
