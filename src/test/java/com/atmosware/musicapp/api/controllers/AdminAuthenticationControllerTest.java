package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.concretes.AdminAuthenticationManager;
import com.atmosware.musicapp.common.constants.Messages;
import com.atmosware.musicapp.core.utils.dto.AuthenticationRequest;
import com.atmosware.musicapp.core.utils.dto.AuthenticationResponse;
import com.atmosware.musicapp.core.utils.dto.RegisterRequest;
import com.atmosware.musicapp.core.utils.dto.RegisterResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.ModelFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class AdminAuthenticationControllerTest {

  @Mock
  AdminAuthenticationManager manager;

  @InjectMocks
  AdminAuthenticationController underTest;

  @Test
  void register() {
    RegisterRequest request = new RegisterRequest();
    request.setEmail("test@test.com");
    request.setPassword("12345");
    request.setFirstName("test");
    request.setLastName("test");
    request.setUsername("test1");
    RegisterResponse response = new RegisterResponse();
    response.setResult(Messages.Authentication.REGISTER_SUCCESSFUL);
    ResponseEntity<RegisterResponse> expected = new ResponseEntity<>(response, HttpStatus.OK);

    lenient().when(manager.register(request)).thenReturn(expected.getBody());


    ResponseEntity<RegisterResponse> newResponse = underTest.register(request);
    RegisterResponse actual = newResponse.getBody();

    assertAll(
            () -> assertNotNull(actual),
            () -> assertEquals(HttpStatus.OK, newResponse.getStatusCode()),
            () -> assertEquals(expected.getBody(), actual),
            () -> assertEquals(expected.getBody().getResult(), actual.getResult())
    );
  }

  @Test
  void authenticate()
  {
    AuthenticationRequest request = new AuthenticationRequest();
    request.setEmail("altan2@altan.com");
    request.setPassword("12345");
    AuthenticationResponse response = new AuthenticationResponse();
    response.setResult(Messages.Authentication.AUTH_SUCCESSFUL);
    ResponseEntity<AuthenticationResponse> expected = new ResponseEntity<>(response, HttpStatus.OK);
    lenient().when(manager.authenticate(request)).thenReturn(expected.getBody());

    ResponseEntity<AuthenticationResponse> newResponse = underTest.authenticate(request);
    AuthenticationResponse actual = newResponse.getBody();

    assertAll(
            () -> assertNotNull(actual),
            () -> assertEquals(HttpStatus.OK, newResponse.getStatusCode()),
            () -> assertEquals(expected.getBody(), actual),
            () -> assertEquals(expected.getBody().getResult(), actual.getResult())
    );
  }
}