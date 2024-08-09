package gr.hua.dev_ops.config;

import gr.hua.dev_ops.entity.Role;
import gr.hua.dev_ops.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RoleInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    @Transactional
    public void init() {
        createRoleIfNotFound("ROLE_CLIENT");
        createRoleIfNotFound("ROLE_BROKER");
        createRoleIfNotFound("ROLE_ADMIN");
    }

    private void createRoleIfNotFound(String roleName) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}
