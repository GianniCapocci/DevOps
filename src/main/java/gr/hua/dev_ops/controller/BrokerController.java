package gr.hua.dev_ops.controller;

import gr.hua.dev_ops.entity.Broker;
import gr.hua.dev_ops.entity.Listing;
import gr.hua.dev_ops.entity.User;
import gr.hua.dev_ops.service.ListingService;
import gr.hua.dev_ops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/broker")
public class BrokerController {
    @Autowired
    private UserService userService;

    @Autowired
    private ListingService listingService;

    @GetMapping("/{brokerId}")
    public User getBroker(@PathVariable Long brokerId) {
        try {
            return userService.getUserById(brokerId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @GetMapping("/current")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerBroker(@RequestBody Broker broker) {
        userService.saveBroker(broker);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listings/{brokerId}")
    public List<Listing> getBrokerListings(@PathVariable Long brokerId) {
        if (brokerId == null || brokerId <= 0) {
            throw new IllegalArgumentException("Invalid broker ID");
        }
        return listingService.findByBrokerId(brokerId);
    }
}
