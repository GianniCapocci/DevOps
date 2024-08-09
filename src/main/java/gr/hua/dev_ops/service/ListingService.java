package gr.hua.dev_ops.service;

import gr.hua.dev_ops.dto.ListingDTO;
import gr.hua.dev_ops.entity.Listing;
import gr.hua.dev_ops.entity.User;
import gr.hua.dev_ops.filter.ListingFilterCriteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ListingService {
    Listing findById(Long id);
    void saveListing(ListingDTO listingDto, List<MultipartFile> images, User currentUser);
    void deleteListing(Long id);
    Listing updateListing(Long listingId, Listing listingDetails);
    List<Listing> getFilteredListings(ListingFilterCriteria criteria);
    List<Listing> getAllListings();
    List<Listing> findByBrokerId(Long brokerId);
    List<Listing> getUnassignedListings();
    boolean claimListing(Long listingId, Long brokerId);
    List<Listing> getAssignedListings();
}
