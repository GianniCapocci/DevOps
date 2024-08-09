package gr.hua.dev_ops.repository;

import gr.hua.dev_ops.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    void deleteById(Long id);
}
