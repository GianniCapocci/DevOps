package gr.hua.dev_ops.repository;

import gr.hua.dev_ops.entity.Listing;
import gr.hua.dev_ops.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ListingRepository extends JpaRepository<Listing, Long> {
//    List<Listing> findAll();
    List<Listing> findByBrokerId(Long brokerId);
    List<Listing> findByBrokerIdIsNull();
    List<Listing> findByBrokerIdIsNotNull();
}
