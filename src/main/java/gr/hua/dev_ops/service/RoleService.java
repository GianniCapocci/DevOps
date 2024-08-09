package gr.hua.dev_ops.service;

import gr.hua.dev_ops.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    List<Role> getAllRoles();
}
