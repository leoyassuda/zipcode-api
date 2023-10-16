package com.ly.zipcode.controllers.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.useCases.addressDetails.AddressDetailsUseCase;
import com.ly.zipcode.utils.AddressUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddressDetailsControllerTest {

  @Mock
  AddressDetailsUseCase addressDetailsUseCase;

  @InjectMocks
  AddressDetailsController addressDetailsController;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  private final AddressDetails addressDetails = AddressUtils.generateAddress(AddressDetails.class);
  private final List<AddressDetails> addressDetailsList = List.of(
      Objects.requireNonNull(AddressUtils.generateAddress(AddressDetails.class)),
      Objects.requireNonNull(AddressUtils.generateAddress(AddressDetails.class)),
      Objects.requireNonNull(AddressUtils.generateAddress(AddressDetails.class))
  );

  @Test
  void findById() {
    when(addressDetailsUseCase.findById(any(UUID.class))).thenReturn(addressDetails);

    var responseEntity = addressDetailsController.findById(UUID.randomUUID());

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(addressDetails.getId(), Objects.requireNonNull(responseEntity.getBody()).getId());
    assertEquals(addressDetails.getZipcode(), responseEntity.getBody().getZipcode());
  }

  @Test
  void findAll() {
    when(addressDetailsUseCase.findAll(anyInt(), anyInt())).thenReturn(addressDetailsList);

    var responseEntity = addressDetailsController.findAll(1, 1);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(addressDetailsList.size(), Objects.requireNonNull(responseEntity.getBody()).size());
  }

  @Test
  void create() {
    when(addressDetailsUseCase.create(any(AddressDetails.class))).thenReturn(addressDetails);

    var responseEntity = addressDetailsController.create(addressDetails);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals(addressDetails.getId(), Objects.requireNonNull(responseEntity.getBody()).getId());
    assertEquals(addressDetails.getZipcode(), responseEntity.getBody().getZipcode());
  }

  @Test
  void update() {
    when(addressDetailsUseCase.update(any(UUID.class), any(AddressDetails.class))).thenReturn(addressDetails);

    var responseEntity = addressDetailsController.update(UUID.randomUUID(), addressDetails);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(addressDetails.getId(), Objects.requireNonNull(responseEntity.getBody()).getId());
    assertEquals(addressDetails.getZipcode(), responseEntity.getBody().getZipcode());
  }

  @Test
  void delete() {
    doNothing().when(addressDetailsUseCase).delete(any(UUID.class));

    var responseEntity = addressDetailsController.delete(UUID.randomUUID());

    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    verify(addressDetailsUseCase).delete(any(UUID.class));
  }
}