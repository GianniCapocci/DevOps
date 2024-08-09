package gr.hua.dev_ops.service;

import gr.hua.dev_ops.entity.Bookmark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookmarkService {
    void addBookmark(Long listingId, double priceThreshold);
    void removeBookmark(Long userId, Long listingId);
    boolean isBookmarked(Long userId, Long listingId);
}
