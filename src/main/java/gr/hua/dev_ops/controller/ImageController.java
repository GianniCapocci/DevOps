package gr.hua.dev_ops.controller;

import gr.hua.dev_ops.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @DeleteMapping("/{imageId}")
    public void deleteImage(@PathVariable Long imageId) {
        try {
            imageService.deleteImage(imageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/add/{listingId}")
    public ResponseEntity<String> uploadImages(@PathVariable Long listingId, @RequestParam("images") List<MultipartFile> images) {
        try {
            imageService.addImagesToListing(listingId, images);
            return ResponseEntity.ok("Images uploaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload images");
        }
    }
}
