package com.ferreirandre.todo_list_api.api.controller.authentication;

import com.ferreirandre.todo_list_api.api.dto.authentication.AuthenticationDTO;
import com.ferreirandre.todo_list_api.api.dto.authentication.RegistrationDTO;
import com.ferreirandre.todo_list_api.domain.model.user.User;
import com.ferreirandre.todo_list_api.infrastructure.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegistrationDTO data) {
        if (this.repository.findByLogin(data.getLogin()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        var newUser = new User(data.getLogin(),encryptedPassword, data.getRole());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
