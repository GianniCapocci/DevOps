package gr.hua.dev_ops.service.impl;

import gr.hua.dev_ops.entity.Bookmark;
import gr.hua.dev_ops.repository.UserRepository;
import gr.hua.dev_ops.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void sendNotificationEmail(Bookmark bookmark) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo((Objects.requireNonNull(userRepository.findById(bookmark.getUser().getId()).orElse(null))).getEmail());
        message.setSubject("Price Alert for Bookmark");
        message.setText("The price of the listing has reached your threshold.");
        javaMailSender.send(message);
    }
}
