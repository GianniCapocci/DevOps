package gr.hua.dev_ops.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gr.hua.dev_ops.entity.Owner;

@Service
public interface OwnerService {
    List<Owner> getAllOwners();
    Owner addOwner(Owner owner);
    Owner findOwnerById(Long id);
}
