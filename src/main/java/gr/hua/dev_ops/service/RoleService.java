package gr.hua.dev_ops.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gr.hua.dev_ops.entity.Role;

@Service
public interface RoleService {
    List<Role> getAllRoles();
}
