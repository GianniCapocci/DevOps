package gr.hua.dev_ops.service;

import gr.hua.dev_ops.entity.Bookmark;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendNotificationEmail(Bookmark bookmark);
}
