package gr.hua.dev_ops.service.impl;

import gr.hua.dev_ops.entity.Bookmark;
import gr.hua.dev_ops.entity.Listing;
import gr.hua.dev_ops.entity.User;
import gr.hua.dev_ops.repository.BookmarkRepository;
import gr.hua.dev_ops.repository.ListingRepository;
import gr.hua.dev_ops.service.BookmarkService;
import gr.hua.dev_ops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public void addBookmark(Long listingId, double priceThreshold) {
        User currentUser = userService.getCurrentUser(); // Method to retrieve the logged-in user
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        if(isBookmarked(currentUser.getId(), listingId)){
            throw new RuntimeException("Bookmark already exists");
        } else {
            Bookmark bookmark = new Bookmark();
            bookmark.setUser(currentUser);
            bookmark.setListing(listing);
            bookmark.setPriceThreshold(priceThreshold);
            bookmark.setNotificationSent(false);

            bookmarkRepository.save(bookmark);
        }
    }

    @Override
    public void removeBookmark(Long userId, Long listingId) {
        Optional<Bookmark> bookmarkOptional = bookmarkRepository.findByUserIdAndListingId(userId, listingId);
        if (bookmarkOptional.isPresent()) {
            bookmarkRepository.delete(bookmarkOptional.get());
        } else {
            throw new IllegalArgumentException("Bookmark not found");
        }
    }

    @Override
    public boolean isBookmarked(Long userId, Long listingId) {
        return bookmarkRepository.findByUserIdAndListingId(userId, listingId).isPresent();
    }
}
