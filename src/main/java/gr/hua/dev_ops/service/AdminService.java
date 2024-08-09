package gr.hua.dev_ops.service;

import gr.hua.dev_ops.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    void deleteUser(Long id);
    User findById(Long id);
}
