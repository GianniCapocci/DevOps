package gr.hua.dev_ops.controller;

import gr.hua.dev_ops.dto.ListingDTO;
import gr.hua.dev_ops.entity.Listing;
import gr.hua.dev_ops.entity.User;
import gr.hua.dev_ops.filter.ListingFilterCriteria;
import gr.hua.dev_ops.service.ListingService;
import gr.hua.dev_ops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/listing")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> createListing(@ModelAttribute ListingDTO listingDto, @RequestPart(required = false) List<MultipartFile> images, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            User currentUser = userService.findByEmail(userDetails.getUsername());
            listingService.saveListing(listingDto, images, currentUser);
            return ResponseEntity.ok("Listing and images saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

    @GetMapping("/{listingId}")
    public Listing getListing(@PathVariable Long listingId) {
        return listingService.findById(listingId);
    }

    @GetMapping("/all")
    public List<Listing> getAllListings() {
        return listingService.getAllListings();
    }

    @GetMapping("/broker/{brokerId}")
    public List<Listing> getListingByBrokerId(@PathVariable Long brokerId) {
        return listingService.findByBrokerId(brokerId);
    }

    @DeleteMapping("/{listingId}")
    public ResponseEntity<?> deleteListing(@PathVariable Long listingId) {
        try {
            listingService.deleteListing(listingId);
            return ResponseEntity.ok("Listing deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete listing");
        }
    }

    @PostMapping("/filtered")
    public List<Listing> getFilteredListings(@RequestBody ListingFilterCriteria filter) {
        return listingService.getFilteredListings(filter);
    }

    @GetMapping("/unassigned")
    public List<Listing> getUnassignedListings() {
        return listingService.getUnassignedListings();
    }

    @GetMapping("/assigned")
    public List<Listing> getAssignedListings() {
        return listingService.getAssignedListings();
    }

    @PostMapping("/claim/{listingId}")
    public ResponseEntity<String> claimListing(@PathVariable Long listingId, @RequestParam Long brokerId) {
        boolean claimed = listingService.claimListing(listingId, brokerId);
        if (claimed) {
            return ResponseEntity.ok("Listing claimed successfully");
        } else {
            return ResponseEntity.status(400).body("Failed to claim listing");
        }
    }
}
