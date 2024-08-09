package gr.hua.dev_ops.repository;

import gr.hua.dev_ops.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
