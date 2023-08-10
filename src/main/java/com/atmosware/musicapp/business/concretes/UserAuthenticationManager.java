package com.atmosware.musicapp.business.concretes;

import com.atmosware.musicapp.business.abstracts.UserAuthenticationService;
import com.atmosware.musicapp.business.rules.UserBusinessRules;
import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.common.utils.annotations.Logger;
import com.atmosware.musicapp.core.security.jwt.JwtService;
import com.atmosware.musicapp.core.utils.dto.AuthenticationRequest;
import com.atmosware.musicapp.core.utils.dto.AuthenticationResponse;
import com.atmosware.musicapp.core.utils.dto.RegisterRequest;
import com.atmosware.musicapp.core.utils.dto.RegisterResponse;
import com.atmosware.musicapp.core.utils.mappers.ModelMapperService;
import com.atmosware.musicapp.entities.User;
import com.atmosware.musicapp.entities.enums.Role;
import com.atmosware.musicapp.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthenticationManager implements UserAuthenticationService {

  private final UserRepository repository;
  private final ModelMapperService mapper;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserBusinessRules userBusinessRules;

  @Override
  @Logger
  public RegisterResponse register(RegisterRequest request) {
    userBusinessRules.checkIfUserExistsByEmailAlternative(request.getEmail());
    userBusinessRules.checkIfUserExistsByUsername(request.getUsername());
    var user = mapper.forRequest().map(request, User.class);
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.USER);
    user.setCreatedAt(LocalDateTime.now());
    repository.save(user);
    RegisterResponse response = new RegisterResponse();
    response.setResult(Messages.Authentication.REGISTER_SUCCESSFUL);
    return response;
  }

  @Override
  @Logger
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    userBusinessRules.checkIfUserExistsByEmail(request.getEmail());
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = repository.findByEmail(request.getEmail());
    HashMap<String, Object> payload = new HashMap<>();
    for (var role : user.getAuthorities()) {
      payload.put(Messages.JwtPayload.ROLES, role.toString());
    }
    payload.put(Messages.JwtPayload.EMAIL, request.getEmail());
    var jwtToken = jwtService.generateToken(payload, user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .result(Messages.Authentication.AUTH_SUCCESSFUL)
        .build();
  }
}
