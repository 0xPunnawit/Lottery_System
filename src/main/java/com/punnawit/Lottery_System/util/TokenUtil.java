package com.punnawit.Lottery_System.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.punnawit.Lottery_System.entity.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TokenUtil {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Value("${JWT_ISSUER}")
    private String jwtIssuer;

    @Value("${JWT_EXPIRATION}")
    private int jwtExpiration;

    public String generateToken(Users users) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return JWT.create()
                .withIssuer(jwtIssuer)
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .withSubject(users.getUserId())
                .withClaim("role", users.getRole().name())
                .sign(algorithm);
    }

    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(jwtIssuer)
                    .build();
            return verifier.verify(token);
        } catch (Exception e) {
            return null;
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(jwtSecret);
    }

}
