package com.example.lab11.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service for handling all operations regarding a Json Web Token.
 */
@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    /**
     * Extracts the username based on the JWT.
     *
     * @param token the given JWT.
     * @return the username of the given JWT.
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date of the JWT.
     *
     * @param token a given JWT.
     * @return the expiration date of the given JWT.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts info from claims with the help of a given function.
     *
     * @param token          the given JWT.
     * @param claimsResolver the function used for extracting information from claims.
     * @param <T>            the output type of the given `claimsResolver`.
     * @return the extracted information.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from a JWT.
     *
     * @param token the given JWT.
     * @return a JWT Claims set.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * Checks if the JWT is still valid.
     *
     * @param token the JWT to be checked.
     * @return <code>true</code> if the given JWT is still valid, otherwise <code>false</code>.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Generates a JWT based on the given <code>UserDetails</code>.
     *
     * @param userDetails a class that stores user details (username and password).
     * @return the newly generated JWT.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Calls the JWT API in order to create a JWT from the given parameters.
     *
     * @param claims  a <code>Map</code> of claims.
     * @param subject the subject of the JWT that is to be created.
     * @return the newly created JWT.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * Validates the given JWT.
     *
     * @param token       the JWT to be validated.
     * @param userDetails the user details that will be used for validation.
     * @return <code>true</code> if the JWT is valid, otherwise <code>false</code>.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
