package gr.hua.dev_ops.controller;

import gr.hua.dev_ops.entity.Client;
import gr.hua.dev_ops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = {"application/json"})
    public ResponseEntity<?> registerClient(@RequestBody Client client) {
        userService.saveClient(client);
        return ResponseEntity.ok().build();
    }
}
