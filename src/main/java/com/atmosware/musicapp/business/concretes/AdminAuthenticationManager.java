package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.AdminAuthenticationService;
import com.atmosware.musicapp.business.rules.AdminBusinessRules;
import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.common.utils.annotations.Logger;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminAuthenticationManager implements AdminAuthenticationService {

  private final AdminRepository repository;
  private final ModelMapperService mapper;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final AdminBusinessRules adminBusinessRules;

  @Override
  @Logger
  public RegisterResponse register(RegisterRequest request) {
    adminBusinessRules.checkIfAdminExistsByEmailAlternative(request.getEmail());
    adminBusinessRules.checkIfAdminExistsByUsername(request.getUsername());
    var admin = mapper.forRequest().map(request, Admin.class);
    admin.setPassword(passwordEncoder.encode(request.getPassword()));
    admin.setRole(Role.ADMIN);
    admin.setCreatedAt(LocalDateTime.now());
    repository.save(admin);
    RegisterResponse response = new RegisterResponse();
    response.setResult(Messages.Authentication.REGISTER_SUCCESSFUL);
    return response;
  }

  @Override
  @Logger
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    adminBusinessRules.checkIfAdminExistsByEmail(request.getEmail());
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var admin = repository.findByEmail(request.getEmail());
    HashMap<String, Object> payload = new HashMap<>();
    for (var role : admin.getAuthorities()) {
      payload.put(Messages.JwtPayload.ROLES, role.toString());
    }
    payload.put(Messages.JwtPayload.EMAIL, request.getEmail());
    var jwtToken = jwtService.generateToken(payload, admin);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .result(Messages.Authentication.AUTH_SUCCESSFUL)
        .build();
  }
}