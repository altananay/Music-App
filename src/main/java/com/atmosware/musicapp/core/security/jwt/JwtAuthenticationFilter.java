package com.atmosware.musicapp.core.security.jwt;

import com.atmosware.musicapp.common.constants.Messages;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    String authHeader = request.getHeader(Messages.JwtRequest.REQUEST_HEADER);
    final String jwt;
    final String username;
    final String email;
    if (authHeader == null || !authHeader.startsWith(Messages.JwtRequest.TOKEN_PREFIX)) {
      filterChain.doFilter(request, response);
      return;
    }
    jwt = authHeader.substring(7);
    try
    {
      username = jwtService.extractUsername(jwt);
      email = jwtService.extractEmail(jwt);
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

        if (jwtService.isTokenValid(jwt, userDetails)) {
          var authorities = new HashSet<GrantedAuthority>(userDetails.getAuthorities().size());
          for (var role : userDetails.getAuthorities())
            authorities.add(
                    new SimpleGrantedAuthority(Messages.JwtRequest.ROLE_PREFIX + role.toString()));
          UsernamePasswordAuthenticationToken authToken =
                  new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
      filterChain.doFilter(request, response);
    }
    catch (ExpiredJwtException e)
    {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write(e.getMessage());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
  }
}