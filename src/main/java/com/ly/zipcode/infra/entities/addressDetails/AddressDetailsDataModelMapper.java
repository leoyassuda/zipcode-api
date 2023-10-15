package com.ly.zipcode.infra.entities.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressDetailsDataModelMapper {
  AddressDetailsDataModel toDataModel(AddressDetails addressDetails);

  List<AddressDetailsDataModel> toDataModel(List<AddressDetails> addressDetailsList);
}
