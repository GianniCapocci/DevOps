package gr.hua.dev_ops.service.impl;

import gr.hua.dev_ops.entity.User;
import gr.hua.dev_ops.repository.UserRepository;
import gr.hua.dev_ops.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
