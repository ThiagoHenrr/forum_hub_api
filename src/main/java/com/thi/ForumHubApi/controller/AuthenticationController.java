package com.thi.ForumHubApi.controller;

import com.thi.ForumHubApi.domain.user.AuthenticationData;
import com.thi.ForumHubApi.domain.user.User;
import com.thi.ForumHubApi.infra.security.JWTokenData;
import com.thi.ForumHubApi.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationData data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        Authentication authentication = manager.authenticate(authenticationToken);

        String tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTokenData(tokenJWT));
    }
}
