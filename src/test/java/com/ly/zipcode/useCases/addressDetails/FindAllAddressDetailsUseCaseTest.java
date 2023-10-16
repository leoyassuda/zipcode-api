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

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FindAllAddressDetailsUseCaseTest {

  @Mock
  AddressDetailsReadDAO addressDetailsReadDAO;

  @Spy
  private AddressDetailsDomainMapper addressDetailsDomainMapper = Mappers.getMapper(AddressDetailsDomainMapper.class);

  @InjectMocks
  private FindAllAddressDetailsUseCase findAllAddressDetailsUseCase;

  @Test
  void execute() {
    var addressDetailsDataModelList = Arrays.asList(
        AddressUtils.generateAddress(AddressDetailsDataModel.class),
        AddressUtils.generateAddress(AddressDetailsDataModel.class),
        AddressUtils.generateAddress(AddressDetailsDataModel.class)
    );

    when(addressDetailsReadDAO.findAll(anyInt(), anyInt())).thenReturn(addressDetailsDataModelList);

    var resultAddressDetailsList = findAllAddressDetailsUseCase.execute(1, 10);

    assertNotNull(resultAddressDetailsList);
    assertEquals(addressDetailsDataModelList.size(), resultAddressDetailsList.size());
  }

}