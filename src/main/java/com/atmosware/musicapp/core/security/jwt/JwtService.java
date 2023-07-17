package com.atmosware.musicapp.core.security.jwt;

import com.atmosware.musicapp.repository.AdminRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private final AdminRepository repository;

    public String extractUsername(String jwt)
    {
        return extractClaim(jwt, Claims::getSubject);
    }

    private Claims extractAllClaims(String jwt)
    {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).getBody();
    }

    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails, Authentication authentication)
    {
        String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        return Jwts.builder().setClaims(extractClaims).claim("claims", roles).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)).signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean isTokenValid(String jwt)
    {
        final String username = extractUsername(jwt);
        return (username.equals(repository.findByUsername(username))) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt)
    {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt)
    {
        return extractClaim(jwt, Claims::getExpiration);
    }


    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails, Authentication authentication)
    {
        return generateToken(new HashMap<>(), userDetails, authentication);
    }

    private Key getSignInKey()
    {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
}