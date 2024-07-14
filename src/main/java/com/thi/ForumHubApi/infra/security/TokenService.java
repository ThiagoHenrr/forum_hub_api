package com.thi.ForumHubApi.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.thi.ForumHubApi.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("forum.hub api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expirationDate())
                    .withClaim("Name ", user.getName())

                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Invalid Signing configuration / Couldn't convert Claims.");
        }
    }

    private Instant expirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
