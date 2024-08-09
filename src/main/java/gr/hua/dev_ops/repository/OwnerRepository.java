package gr.hua.dev_ops.repository;

import gr.hua.dev_ops.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
