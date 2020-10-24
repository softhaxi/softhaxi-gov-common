package com.softhaxi.marves.core.util;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * @author Raja Sihombing
 * @since 1
 */
@Component
public class AccessTokenUtil implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5105988722775150059L;

    public static final long TOKEN_VALIDITY = 1000 * 3600;

    @Value("${app.secret.key}")
    private String secretKey;

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    // while creating the token -
    // 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
    // 2. Sign the JWT using the HS512 algorithm and secret key.
    // 3. According to JWS Compact
    // compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        // SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        // String base64Key = Encoders.BASE64.encode(key.getEncoded());
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(key)
                // .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        // return base64Key;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return (Claims) Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parse(token)
            .getBody();
        // return Jwts.parser().setSigningKey(key)
        // .parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, String userid) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userid) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
}
