package com.ly.zipcode.domains.addressDetails;

import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressDetailsDomainMapper {
    AddressDetails toDomain(AddressDetailsDataModel addressDetailsDataModel);

    List<AddressDetails> toDomain(List<AddressDetailsDataModel> addressDetailsDataModelList);
}
