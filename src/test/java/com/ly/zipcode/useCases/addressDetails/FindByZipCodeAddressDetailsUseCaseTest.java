package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.domains.addressDetails.AddressDetailsDomainMapper;
import com.ly.zipcode.infra.adapters.api.ViaCEPClient;
import com.ly.zipcode.infra.adapters.api.ViaCEPResponseMapper;
import com.ly.zipcode.infra.repositories.addressDetails.read.AddressDetailsReadDAO;
import com.ly.zipcode.utils.AddressUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindByZipCodeAddressDetailsUseCaseTest {

  @Mock
  ViaCEPClient viaCEPClient;

  @Mock
  AddressDetailsReadDAO addressDetailsReadDAO;

  @Mock
  UpsertAddressDetailsUseCase upsertAddressDetailsUseCase;

  @Spy
  AddressDetailsDomainMapper addressDetailsDomainMapper = Mappers.getMapper(AddressDetailsDomainMapper.class);

  ViaCEPResponseMapper viaCEPResponseMapper = Mappers.getMapper(ViaCEPResponseMapper.class);

  @InjectMocks
  FindByZipCodeAddressDetailsUseCase findByZipCodeAddressDetailsUseCase;

  @Test
  void execute() {
    var zipcode = "12345-678";
    var addressDetails = AddressUtils.generateAddress(AddressDetails.class);

    when(viaCEPClient.getAddressDetailsByZipcode(zipcode)).thenReturn(viaCEPResponseMapper.mapToCEPResponse(
        addressDetails));
    when(upsertAddressDetailsUseCase.execute(any(AddressDetails.class))).thenReturn(addressDetails);

    var result = findByZipCodeAddressDetailsUseCase.execute(zipcode);
    assertNotNull(result.getId());
    assertEquals(addressDetails.getZipcode(), result.getZipcode());

  }
}