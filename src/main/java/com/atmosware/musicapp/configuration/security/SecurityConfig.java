package com.atmosware.musicapp.configuration.security;

import com.atmosware.musicapp.core.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf().disable().authorizeHttpRequests().requestMatchers("/admin/auth/**", "/admins/**",
         "/v2/api-docs",
         "/v3/api-docs",
         "/v3/api-docs/**",
         "/swagger-resources",
         "/swagger-resources/**",
         "/configuration/ui",
         "/configuration/security",
         "/swagger-ui/**",
         "/webjars/**",
         "/swagger-ui.html").permitAll().requestMatchers(HttpMethod.GET, "/songs", "/artistsongs", "/artists","/artistalbums", "/albums").permitAll().requestMatchers("/songs/**", "/albums/**", "/artists/**").hasAnyRole("ADMIN").anyRequest()
                .authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}