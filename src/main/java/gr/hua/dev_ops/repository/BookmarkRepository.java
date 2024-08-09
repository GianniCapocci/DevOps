package gr.hua.dev_ops.repository;

import gr.hua.dev_ops.entity.Bookmark;
import gr.hua.dev_ops.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUserId(Long userId);
    List<Bookmark> findByListingId(Long listingId);
    Optional<Bookmark> findByUserIdAndListingId(Long userId, Long listingId);
}
