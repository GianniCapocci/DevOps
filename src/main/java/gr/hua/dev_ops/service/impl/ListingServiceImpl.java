package gr.hua.dev_ops.service.impl;

import gr.hua.dev_ops.dto.ListingDTO;
import gr.hua.dev_ops.entity.Bookmark;
import gr.hua.dev_ops.entity.Image;
import gr.hua.dev_ops.entity.Listing;
import gr.hua.dev_ops.entity.User;
import gr.hua.dev_ops.filter.ListingFilterCriteria;
import gr.hua.dev_ops.repository.BrokerRepository;
import gr.hua.dev_ops.repository.ListingRepository;
import gr.hua.dev_ops.service.EmailService;
import gr.hua.dev_ops.service.ImageService;
import gr.hua.dev_ops.service.ListingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {

    @Value("${upload.dir}")
    private String uploadDir;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private BrokerRepository brokerRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Listing findById(Long id) {
        return listingRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveListing(ListingDTO listingDto, List<MultipartFile> images, User currentUser) {
        Listing listing = new Listing();
        listing.setStreet(listingDto.getStreet());
        listing.setArea(listingDto.getArea());
        listing.setAreaCode(listingDto.getAreaCode());
        listing.setPrice(listingDto.getPrice());
        listing.setSquareMeters(listingDto.getSquareMeters());
        listing.setBuiltDate(listingDto.getBuiltDate());
        listing.setFloor(listingDto.getFloor());
        listing.setOwnerId(listingDto.getOwnerId());

        if (currentUser.getRoles().stream().anyMatch(role -> "ROLE_BROKER".equals(role.getName()))) {
            listing.setBrokerId(currentUser.getId());
        }

        Listing savedListing = listingRepository.save(listing);

        imageService.addImagesToListing(savedListing.getId(), images);


    }

    @Override
    public void deleteListing(Long id) {
        Listing listing = listingRepository.findById(id).orElse(null);
        // Delete associated images
        List<Image> images = listing.getImages(); // Retrieve the list of Image entities
        if (images != null) {
            for (Image image : images) {
                String fileName = image.getFileName(); // Get the file name from the Image entity
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
            }
        }
        listingRepository.delete(listing);
    }

    @Override
    @Transactional
    public Listing updateListing(Long listingId, Listing listingDetails) {
        return listingRepository.findById(listingId).map(listing -> {
            listing.setStreet(listingDetails.getStreet());
            listing.setArea(listingDetails.getArea());
            listing.setAreaCode(listingDetails.getAreaCode());
            listing.setPrice(listingDetails.getPrice());
            listing.setSquareMeters(listingDetails.getSquareMeters());
            listing.setBuiltDate(listingDetails.getBuiltDate());
            listing.setFloor(listingDetails.getFloor());

            for (Bookmark bookmark : listing.getBookmarks()) {
                if (!bookmark.getNotificationSent() && listingDetails.getPrice() <= bookmark.getPriceThreshold()) {
                    emailService.sendNotificationEmail(bookmark); // Send email
                    bookmark.setNotificationSent(true); // Uspdate bookmark status
                }
            }

            return listingRepository.save(listing);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + listingId));
    }

    @Override
    public List<Listing> getFilteredListings(ListingFilterCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Listing> query = cb.createQuery(Listing.class);
        Root<Listing> root = query.from(Listing.class);
        List<Predicate> predicates = new ArrayList<>();

        // Build predicates based on filter criteria
        if (criteria.getMinPrice() != null) {
            predicates.add(cb.ge(root.get("price"), criteria.getMinPrice()));
        }

        if (criteria.getMaxPrice() != null) {
            predicates.add(cb.le(root.get("price"), criteria.getMaxPrice()));
        }

        if (criteria.getStartDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("builtDate"), criteria.getStartDate()));
        }

        if (criteria.getEndDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("builtDate"), criteria.getEndDate()));
        }

        if (criteria.getStreet() != null && !criteria.getStreet().isEmpty()) {
            predicates.add(cb.like(root.get("street"), "%" + criteria.getStreet() + "%"));
        }

        if (criteria.getArea() != null && !criteria.getArea().isEmpty()) {
            predicates.add(cb.equal(root.get("area"), criteria.getArea()));
        }

        if (criteria.getAreaCode() != null) {
            predicates.add(cb.equal(root.get("areaCode"), criteria.getAreaCode()));
        }

        if (criteria.getMinSquareMeters() != null) {
            predicates.add(cb.ge(root.get("squareMeters"), criteria.getMinSquareMeters()));
        }

        if (criteria.getMaxSquareMeters() != null) {
            predicates.add(cb.le(root.get("squareMeters"), criteria.getMaxSquareMeters()));
        }

        if (criteria.getMinFloor() != null) {
            predicates.add(cb.ge(root.get("floor"), criteria.getMinFloor()));
        }

        if (criteria.getMaxFloor() != null) {
            predicates.add(cb.le(root.get("floor"), criteria.getMaxFloor()));
        }

        predicates.add(cb.isNotNull(root.get("brokerId")));

        // Apply predicates to the query
        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    @Override
    public List<Listing> findByBrokerId(Long brokerId) {
        return listingRepository.findByBrokerId(brokerId);
    }

    @Override
    public List<Listing> getUnassignedListings() {
        return listingRepository.findByBrokerIdIsNull();
    }

    @Override
    @Transactional
    public boolean claimListing(Long listingId, Long brokerId) {
        return listingRepository.findById(listingId)
                .map(listing -> {
                    listing.setBrokerId(brokerId);
                    listing.setBroker(brokerRepository.findById(brokerId).get());
                    listingRepository.save(listing);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public List<Listing> getAssignedListings() {
        return listingRepository.findByBrokerIdIsNotNull();
    }
}
