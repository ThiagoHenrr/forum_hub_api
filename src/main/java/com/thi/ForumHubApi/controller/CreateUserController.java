package com.thi.ForumHubApi.controller;

import com.thi.ForumHubApi.domain.user.User;
import com.thi.ForumHubApi.domain.user.UserData;
import com.thi.ForumHubApi.domain.user.UserDetailsData;
import com.thi.ForumHubApi.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class CreateUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid UserData userData){
        String encodedPassword = passwordEncoder.encode(userData.password());
        User newUser = new User(userData.name(), userData.email(), encodedPassword);
        repository.save(newUser);

        return ResponseEntity.ok(new UserDetailsData(newUser));
    }
}
