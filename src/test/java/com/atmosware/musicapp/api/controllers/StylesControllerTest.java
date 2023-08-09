package com.atmosware.musicapp.api.controllers;

import com.atmosware.musicapp.business.concretes.StyleManager;
import com.atmosware.musicapp.business.dto.requests.create.CreateStyleRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateStyleResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StylesControllerTest {

  @Mock StyleManager mockStyleManager;

  @InjectMocks StylesController underTest;

  @Test
  void getAll() {}

  @Test
  void getById() {}

  @Test
  void add_shouldAddSuccessfully() {
    CreateStyleRequest request = new CreateStyleRequest();
    request.setName("junit test");
    CreateStyleResponse expected = new CreateStyleResponse();
    expected.setName("junit test");
    when(mockStyleManager.add(request)).thenReturn(expected);

    CreateStyleResponse response = underTest.add(request);

    assertAll(
        () -> assertNotNull(response),
        () -> assertEquals(request.getName(), response.getName()));
  }

  @Test
  void update() {}

  @Test
  void delete() {}
}
