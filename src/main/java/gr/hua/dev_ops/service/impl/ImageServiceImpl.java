package gr.hua.dev_ops.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import gr.hua.dev_ops.entity.Image;
import gr.hua.dev_ops.entity.Listing;
import gr.hua.dev_ops.repository.ImageRepository;
import gr.hua.dev_ops.repository.ListingRepository;
import gr.hua.dev_ops.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${upload.dir}")
    private String uploadDir;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public void deleteImage(Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        if (image != null) {
            String fileName = image.getFileName();
            if (fileName != null) {
                File file = new File(uploadDir + File.separator + fileName);
                if (file.exists()) {
                    if (file.delete()) {
                        System.out.println("Deleted file: " + file.getAbsolutePath());
                    } else {
                        System.err.println("Failed to delete file: " + file.getAbsolutePath());
                    }
                } else {
                    System.out.println("File not found: " + file.getAbsolutePath());
                }
            } else {
                System.err.println("File name is null for image: " + image);
            }
            imageRepository.deleteById(id);
        }
    }

    @Override
    public void addImagesToListing(Long listingId, List<MultipartFile> images) {
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found with id " + listingId));

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        for (MultipartFile image : images) {
            if (image.isEmpty()) {
                continue; // Skip empty files
            }

            try {
                // Define the path for the file
                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                File file = new File(uploadDir + fileName);

                // Save the file to the specified location
                Files.copy(image.getInputStream(), Paths.get(file.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

                Image img = new Image();
                img.setFileName(fileName);
                img.setContentType(image.getContentType());
                img.setListing(listing);

                imageRepository.save(img);
            } catch (IOException e) {
                System.out.println("Error: " +e.getMessage());
            }
        }
    }
}
