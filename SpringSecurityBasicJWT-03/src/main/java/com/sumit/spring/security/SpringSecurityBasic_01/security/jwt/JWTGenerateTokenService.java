package com.sumit.spring.security.SpringSecurityBasic_01.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTGenerateTokenService {

  private String secret_key = "";

  public JWTGenerateTokenService() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
      SecretKey sk = keyGen.generateKey();
      secret_key = Base64.getEncoder().encodeToString(sk.getEncoded());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public String generateToken(String username) {

    Map<String, Object> claims = new HashMap<>();

    return Jwts.builder()
        .claims()
        .add(claims)
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 60 * 30))
        .and()
        .signWith(getKey())
        .compact();
  }

  public SecretKey getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret_key); // converting to byte
    // byte[] keyBytes =  secret_key.getBytes();// converting to byte
    return Keys.hmacShaKeyFor(keyBytes); // Keys.hmacShaKeyFor takes byte as an input not string
  }
}
