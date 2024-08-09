package gr.hua.dev_ops.controller;

import gr.hua.dev_ops.service.BookmarkService;
import gr.hua.dev_ops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmark")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private UserService userService;

    @PostMapping("/add/{listingId}")
    public ResponseEntity<?> addBookmark(@PathVariable Long listingId, @RequestParam double priceThreshold) { // Accept priceThreshold as a query parameter
        try {
            bookmarkService.addBookmark(listingId, priceThreshold);
            return ResponseEntity.ok("Listing bookmarked successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to bookmark listing");
        }
    }

    @PostMapping("/remove/{listingId}")
    public ResponseEntity<?> removeBookmark(@PathVariable Long listingId) {
        Long userId = userService.getCurrentUser().getId(); // Retrieve the user ID from the current session or security context
        try {
            bookmarkService.removeBookmark(userId, listingId);
            return ResponseEntity.ok("Bookmark removed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove bookmark");
        }
    }

    @GetMapping("/bookmarked/{listingId}")
    public ResponseEntity<Boolean> isBookmarked(@PathVariable Long listingId) {
        Long userId = userService.getCurrentUser().getId(); // Retrieve the user ID from the current session or security context
        boolean bookmarked = bookmarkService.isBookmarked(userId, listingId);
        return ResponseEntity.ok(bookmarked);
    }
}
