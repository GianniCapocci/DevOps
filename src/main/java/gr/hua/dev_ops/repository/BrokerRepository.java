package gr.hua.dev_ops.repository;

import gr.hua.dev_ops.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerRepository extends JpaRepository<Broker, Long> {
}
