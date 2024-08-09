package gr.hua.dev_ops.service;

import gr.hua.dev_ops.entity.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OwnerService {
    List<Owner> getAllOwners();
    Owner addOwner(Owner owner);
    Owner findOwnerById(Long id);
}
