package gr.hua.dev_ops.service;

import gr.hua.dev_ops.entity.Broker;
import gr.hua.dev_ops.entity.Client;
import gr.hua.dev_ops.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUserById(Long id);
    User updateUser(Long userId, User userDetails);
    List<User> findAll();
    User getCurrentUser();
    void saveClient(Client client);
    User saveBroker(Broker broker);
    User findByEmail(String email);
}
