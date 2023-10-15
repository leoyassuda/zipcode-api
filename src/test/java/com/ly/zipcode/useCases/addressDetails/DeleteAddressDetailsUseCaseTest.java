package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.infra.repositories.addressDetails.write.AddressDetailsWriteDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class DeleteAddressDetailsUseCaseTest {

  @Mock
  AddressDetailsWriteDAO addressDetailsWriteDAO;

  @InjectMocks
  DeleteAddressDetailsUseCase deleteAddressDetailsUseCase;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void execute() {
    var uuid = UUID.randomUUID();
    doNothing().when(addressDetailsWriteDAO).delete(any(UUID.class));

    deleteAddressDetailsUseCase.execute(uuid);

    verify(addressDetailsWriteDAO).delete(any(UUID.class));
  }
}