package gr.hua.dev_ops.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    void deleteImage(Long id);
    void addImagesToListing(Long listingId, List<MultipartFile> images);
}
