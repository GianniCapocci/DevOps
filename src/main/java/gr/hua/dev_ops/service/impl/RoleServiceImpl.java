package gr.hua.dev_ops.service.impl;

import gr.hua.dev_ops.entity.Role;
import gr.hua.dev_ops.repository.RoleRepository;
import gr.hua.dev_ops.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
