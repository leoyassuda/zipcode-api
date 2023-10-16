package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.domains.addressDetails.AddressDetailsDomainMapper;
import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModel;
import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModelMapper;
import com.ly.zipcode.infra.repositories.addressDetails.read.AddressDetailsReadDAO;
import com.ly.zipcode.infra.repositories.addressDetails.write.AddressDetailsWriteDAO;
import com.ly.zipcode.utils.AddressUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.ly.zipcode.utils.AddressUtils.generateAddress;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateAddressDetailsUseCaseTest {
  @Mock
  AddressDetailsWriteDAO addressDetailsWriteDAO;

  @Mock
  AddressDetailsReadDAO addressDetailsReadDAO;

  @Spy
  AddressDetailsDomainMapper addressDetailsDomainMapper = Mappers.getMapper(AddressDetailsDomainMapper.class);

  @Spy
  AddressDetailsDataModelMapper addressDetailsDataModel = Mappers.getMapper(AddressDetailsDataModelMapper.class);

  @InjectMocks
  UpdateAddressDetailsUseCase updateAddressDetailsUseCase;

  @Test
  void execute() {
    var addressDetailsDataModelTest = generateAddress(AddressDetailsDataModel.class);
    var id = addressDetailsDataModelTest.getId();
    when(addressDetailsWriteDAO.update(any(UUID.class), any(AddressDetailsDataModel.class))).thenReturn(1);
    when(addressDetailsReadDAO.findById(any(UUID.class))).thenReturn(addressDetailsDataModelTest);

    var addressDetailsUpdated = updateAddressDetailsUseCase.execute(id,
        AddressUtils.generateAddress(AddressDetails.class));

    verify(addressDetailsWriteDAO).update(any(UUID.class), any(AddressDetailsDataModel.class));
    verify(addressDetailsReadDAO).findById(any(UUID.class));
    assertNotNull(addressDetailsUpdated);
    assertEquals(addressDetailsDataModelTest.getId(), addressDetailsUpdated.getId());

  }
}