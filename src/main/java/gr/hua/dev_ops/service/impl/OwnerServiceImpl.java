package gr.hua.dev_ops.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.hua.dev_ops.entity.Owner;
import gr.hua.dev_ops.repository.OwnerRepository;
import gr.hua.dev_ops.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    @Transactional
    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner findOwnerById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }
}
