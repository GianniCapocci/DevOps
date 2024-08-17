package gr.hua.dev_ops.service;

import org.springframework.stereotype.Service;

import gr.hua.dev_ops.entity.User;

@Service
public interface AdminService {
    void deleteUser(Long id);
    User findById(Long id);
}
