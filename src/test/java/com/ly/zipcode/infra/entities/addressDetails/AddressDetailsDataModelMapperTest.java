package com.ly.zipcode.infra.entities.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.utils.AddressUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AddressDetailsDataModelMapperImpl.class })
class AddressDetailsDataModelMapperTest {

  @Autowired
  private AddressDetailsDataModelMapper mapper;

  @Test
  void givenDomain_whenCallMapping_thenReturnCorrectDataModel() {
    var addressMocked = AddressUtils.generateAddress(AddressDetails.class);

    var addressResult = mapper.toDataModel(addressMocked);

    assertNotNull(addressMocked);
    assertEquals(addressMocked.getId(), addressResult.getId());
    assertEquals(addressMocked.getZipcode(), addressResult.getZipcode());
  }

  @Test
  void givenDomainList_whenCallMapping_thenReturnCorrectDataModelList() {
    var addressDetailsList = Arrays.asList(
        AddressUtils.generateAddress(AddressDetails.class),
        AddressUtils.generateAddress(AddressDetails.class)
    );

    var addressResultList = mapper.toDataModel(addressDetailsList);

    assertEquals(2, addressResultList.size());
    assertEquals(addressDetailsList.get(0).getZipcode(), addressResultList.get(0).getZipcode());
  }
}