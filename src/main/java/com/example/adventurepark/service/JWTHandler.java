package com.example.adventurepark.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
public class JWTHandler {

    private static final String secretEnvName = "JWT_SECRET";
    private static final String issuerEnvName = "JWT_ISSUER";

    private String accessToken;
    private String username;

    public void sign(String employeeUsername) {

        LocalDateTime localDateTime = LocalDateTime.now().plusHours(2);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(instant);

        try {
            Algorithm algorithm = Algorithm.HMAC256(System.getenv(secretEnvName));
            this.accessToken = JWT.create()
                    .withIssuer(System.getenv(issuerEnvName))
                    .withClaim("username", employeeUsername)
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "jwt configuration error");
        }
    }

    public void decode(String jwt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(System.getenv(secretEnvName));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(System.getenv(issuerEnvName))
                    .build();
            
            //Will throw error here if not valid
            DecodedJWT decodedJWT = verifier.verify(jwt);

            Claim usernameClaim = decodedJWT.getClaim("username");
            this.username = usernameClaim.asString();
        } catch (SignatureVerificationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "JWT verification error");
        }
    }
}
