package gr.hua.dev_ops.service;

import org.springframework.stereotype.Service;

@Service
public interface BookmarkService {
    void addBookmark(Long listingId, double priceThreshold);
    void removeBookmark(Long userId, Long listingId);
    boolean isBookmarked(Long userId, Long listingId);
}
