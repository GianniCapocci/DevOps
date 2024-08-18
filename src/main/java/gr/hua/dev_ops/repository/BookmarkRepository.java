package gr.hua.dev_ops.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.hua.dev_ops.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByUserIdAndListingId(Long userId, Long listingId);
}
