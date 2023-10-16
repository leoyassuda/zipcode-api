package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetailsDomainMapper;
import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModel;
import com.ly.zipcode.infra.repositories.addressDetails.read.AddressDetailsReadDAO;
import com.ly.zipcode.utils.AddressUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindByIdAddressDetailsUseCaseTest {

  @Mock
  AddressDetailsReadDAO addressDetailsReadDAO;

  @Spy
  AddressDetailsDomainMapper addressDetailsDomainMapper = Mappers.getMapper(AddressDetailsDomainMapper.class);

  @InjectMocks
  FindByIdAddressDetailsUseCase findByIdAddressDetailsUseCase;

  @Test
  void execute() {
    var uuid = UUID.randomUUID();

    when(addressDetailsReadDAO.findById(any(UUID.class))).thenReturn(AddressUtils.generateAddress(
        AddressDetailsDataModel.class));

    var addressDetails = findByIdAddressDetailsUseCase.execute(uuid);
    assertNotNull(addressDetails);
  }
}