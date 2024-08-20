package gr.hua.dev_ops.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.hua.dev_ops.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
