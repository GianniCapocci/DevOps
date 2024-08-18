package gr.hua.dev_ops.service.impl;

import gr.hua.dev_ops.entity.*;
import gr.hua.dev_ops.repository.*;
import gr.hua.dev_ops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setFirst_name(userDetails.getFirst_name());
            user.setLast_name(userDetails.getLast_name());
            user.setEmail(userDetails.getEmail());
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));

            user.getRoles().clear();

            // Fetch and set new roles
            List<Role> newRoles = userDetails.getRoles().stream()
                    .map(role -> roleRepository.findById(role.getId()).orElse(null))
                    .collect(Collectors.toList());

            user.setRoles(newRoles);

            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));

        Role clientRole = roleRepository.findByName("ROLE_CLIENT")
                .orElseThrow(() -> new RuntimeException("Error: Role ROLE_CLIENT is not found."));

        client.setRoles(Collections.singletonList(clientRole));
        userRepository.save(client);
    }

    @Override
    public void saveBroker(Broker broker) {
        broker.setPassword(passwordEncoder.encode(broker.getPassword()));

        Role brokerRole = roleRepository.findByName("ROLE_BROKER")
                .orElseThrow(() -> new RuntimeException("Error: Role ROLE_BROKER is not found."));

        broker.setRoles(Collections.singletonList(brokerRole));
        userRepository.save(broker);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }
        throw new RuntimeException("User is not authenticated");
    }
}
