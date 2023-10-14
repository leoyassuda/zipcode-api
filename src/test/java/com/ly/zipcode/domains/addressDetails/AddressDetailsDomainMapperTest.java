package com.ly.zipcode.domains.addressDetails;

import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModel;
import com.ly.zipcode.utils.AddressUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AddressDetailsDomainMapperImpl.class})
class AddressDetailsDomainMapperTest {

    @Autowired
    private AddressDetailsDomainMapper mapper;

    @Test
    void givenDataModel_whenCallMapping_thenReturnCorrectDomain() {
        var addressMocked = AddressUtils.generateAddress(AddressDetailsDataModel.class);

        var addressResult = mapper.toDomain(addressMocked);

        assertNotNull(addressMocked);
        assertEquals(addressMocked.getId(), addressResult.getId());
        assertEquals(addressMocked.getZipcode(), addressResult.getZipcode());

    }

    @Test
    void givenDataModelList_whenCallMapping_thenReturnCorrectDomainList() {
        var addressDetailsDataModelList = Arrays.asList(
                AddressUtils.generateAddress(AddressDetailsDataModel.class),
                AddressUtils.generateAddress(AddressDetailsDataModel.class)
        );

        var addressResultList = mapper.toDomain(addressDetailsDataModelList);

        assertEquals(2, addressResultList.size());
        assertEquals(addressDetailsDataModelList.get(0).getZipcode(), addressResultList.get(0).getZipcode());

    }

}