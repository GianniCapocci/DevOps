package gr.hua.dev_ops.controller;

import gr.hua.dev_ops.entity.User;
import gr.hua.dev_ops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gr.hua.dev_ops.entity.Role;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/roles")
    public ResponseEntity<List<String>> getCurrentUserRoles() {
        User user = userService.getCurrentUser();

        List<String> roles = user.getRoles().stream()
                .map(Role::getName) // Convert each Role to its name (String)
                .collect(Collectors.toList());

        return ResponseEntity.ok(roles);
    }

}
