package com.atmosware.musicapp.api.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.atmosware.musicapp.business.concretes.StyleManager;
import com.atmosware.musicapp.business.dto.requests.create.CreateStyleRequest;
import com.atmosware.musicapp.business.dto.requests.update.UpdateStyleRequest;
import com.atmosware.musicapp.business.dto.responses.create.CreateStyleResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetAllStylesResponse;
import com.atmosware.musicapp.business.dto.responses.get.GetStyleResponse;
import com.atmosware.musicapp.business.dto.responses.update.UpdateStyleResponse;
import com.atmosware.musicapp.core.exceptions.BusinessException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class StylesControllerTest {

  @Mock StyleManager mockStyleManager;

  @InjectMocks StylesController underTest;

  @Test
  void getAll_shouldReturnGetAllStylesResponse() {
    GetAllStylesResponse getAllStylesResponse = new GetAllStylesResponse();
    List<GetAllStylesResponse> expected = Arrays.asList(getAllStylesResponse);

    when(mockStyleManager.getAll()).thenReturn(expected);

    List<GetAllStylesResponse> responses = underTest.getAll();

    assertAll(
        () -> assertNotNull(responses),
        () -> assertEquals(expected.size(), responses.size()),
        () -> assertEquals(expected, responses));
  }

  @Test
  void getById_shouldReturnGetStyleResponse() {
    GetStyleResponse expected = new GetStyleResponse();
    expected.setName("test");
    UUID id = UUID.randomUUID();
    expected.setId(id);

    when(mockStyleManager.getById(id)).thenReturn(expected);

    GetStyleResponse response = underTest.getById(id);

    assertAll(
        () -> assertNotNull(response),
        () -> assertEquals(expected.getName(), response.getName()),
        () -> assertEquals(expected.getId(), response.getId()));
  }

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
        () -> assertEquals(request.getName(), response.getName()),
        () -> assertNotEquals("", response.getCreatedAt()));
  }

  @Test
  void update_shouldUpdateSuccessfully() {
    UUID id = UUID.randomUUID();
    UpdateStyleRequest request = new UpdateStyleRequest();
    request.setName("test");
    UpdateStyleResponse response = new UpdateStyleResponse();
    response.setName("test");
    response.setId(id);
    when(mockStyleManager.update(id, request)).thenReturn(response);
    UpdateStyleResponse expected = underTest.update(id, request);

    assertAll(
        () -> assertNotNull(response), () -> assertEquals(request.getName(), expected.getName()));
  }

  @Test
  void add_shouldThrowBusinessException_whenStyleAlreadyExists() {
    CreateStyleRequest request = new CreateStyleRequest();
    request.setName("test");
    Mockito.doThrow(BusinessException.class).when(mockStyleManager).add(request);

    assertThrows(
        BusinessException.class,
        () -> when(underTest.add(request)).thenThrow(BusinessException.class));
  }

  @Test
  void delete_shouldDeleteSuccessfully() {
    UUID id = UUID.randomUUID();
    ResponseEntity<Void> response = underTest.delete(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  void delete_shouldThrowBusinessException_whenStyleIdNotExists() {

    UUID id = UUID.randomUUID();
    Mockito.doThrow(BusinessException.class).when(mockStyleManager).delete(id);

    assertThrows(
        BusinessException.class,
        () -> when(underTest.delete(id)).thenThrow(BusinessException.class));
  }
}