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
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAddressDetailsUseCaseTest {

  @Mock
  AddressDetailsWriteDAO addressDetailsWriteDAO;

  @Mock
  AddressDetailsReadDAO addressDetailsReadDAORead;

  @Spy
  AddressDetailsDomainMapper addressDetailsDomainMapper = Mappers.getMapper(AddressDetailsDomainMapper.class);

  @Spy
  AddressDetailsDataModelMapper addressDetailsDataModelMapper = Mappers.getMapper(AddressDetailsDataModelMapper.class);

  @Test
  void execute() {
    var addressDetails = AddressUtils.generateAddress(AddressDetails.class);

    when(addressDetailsWriteDAO.create(any(AddressDetailsDataModel.class))).thenReturn(UUID.randomUUID());
    when(addressDetailsReadDAORead.findById(any(UUID.class))).thenReturn(AddressUtils.generateAddress(AddressDetailsDataModel.class));


  }
}