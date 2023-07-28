package com.atmosware.musicapp.core.security.jwt;

import com.atmosware.musicapp.repository.AdminRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

  private static final String SECRET_KEY =
      "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

  public String extractUsername(String jwt) {
    return extractClaim(jwt, Claims::getSubject);
  }

  private Claims extractAllClaims(String jwt) {
    return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).getBody();
  }

  public String extractEmail(String jwt) {
    var email =
        Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parse(jwt)
            .getBody()
            .toString()
            .split(",")[1]
            .substring(7);
    return email;
  }

  public String generateToken(
      Map<String, Object> extractClaims, UserDetails userDetails) {
    // String roles =
    // authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

    return Jwts.builder()
        .setClaims(extractClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(String jwt, UserDetails userDetails) {
    final String username = extractUsername(jwt);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
  }

  public boolean isTokenExpired(String jwt) {
    return extractExpiration(jwt).before(new Date());
  }

  private Date extractExpiration(String jwt) {
    return extractClaim(jwt, Claims::getExpiration);
  }

  public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(jwt);
    return claimsResolver.apply(claims);
  }

  private Key getSignInKey() {
    byte[] key = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(key);
  }
}
