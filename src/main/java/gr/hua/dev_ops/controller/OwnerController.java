package gr.hua.dev_ops.controller;

import gr.hua.dev_ops.entity.Owner;
import gr.hua.dev_ops.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/all")
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @PostMapping("/add")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        try {
            Owner savedOwner = ownerService.addOwner(owner);
            return ResponseEntity.ok(savedOwner); // Return the created owner with ID
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<Owner> getOwner(@PathVariable Long ownerId) {
        Owner owner = ownerService.findOwnerById(ownerId);
        if (owner != null) {
            return ResponseEntity.ok(owner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
