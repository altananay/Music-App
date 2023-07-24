package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.AdminAuthenticationService;
import com.atmosware.musicapp.core.security.jwt.JwtService;
import com.atmosware.musicapp.core.utils.dto.AuthenticationRequest;
import com.atmosware.musicapp.core.utils.dto.AuthenticationResponse;
import com.atmosware.musicapp.core.utils.dto.RegisterRequest;
import com.atmosware.musicapp.core.utils.dto.RegisterResponse;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.Admin;
import com.atmosware.musicapp.entities.enums.Role;
import com.atmosware.musicapp.repository.AdminRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminAuthenticationManager implements AdminAuthenticationService {

    private final AdminRepository repository;
    private final ModelMapperService mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        var admin = mapper.forRequest().map(request, Admin.class);
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRole(Role.ADMIN);
        admin.setCreatedAt(LocalDateTime.now());
        repository.save(admin);
        RegisterResponse response = new RegisterResponse();
        response.setResult("Başarıyla kayıt oldunuz.");
        return response;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        var admin = repository.findByEmail(request.getEmail());
        HashMap<String, Object> payload = new HashMap<>();
        for (var role: admin.getAuthorities())
        {
            payload.put("roles", role.toString());
        }
        payload.put("email", request.getEmail());
        var jwtToken = jwtService.generateToken(payload, admin);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}