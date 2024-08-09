package gr.hua.dev_ops.repository;

import gr.hua.dev_ops.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
