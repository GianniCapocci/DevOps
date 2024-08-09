package gr.hua.dev_ops.repository;

import gr.hua.dev_ops.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
