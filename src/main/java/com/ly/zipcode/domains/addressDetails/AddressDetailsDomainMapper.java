package com.ly.zipcode.domains.addressDetails;

import com.ly.zipcode.infra.adapters.api.CEPResponse;
import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressDetailsDomainMapper {
  AddressDetails toDomain(AddressDetailsDataModel addressDetailsDataModel);

  List<AddressDetails> toDomain(List<AddressDetailsDataModel> addressDetailsDataModelList);

  @Mapping(target = "zipcode", source = "cep")
  @Mapping(target = "address", source = "logradouro")
  @Mapping(target = "additionalAddress", source = "complemento")
  @Mapping(target = "neighborhood", source = "bairro")
  @Mapping(target = "city", source = "localidade")
  @Mapping(target = "state", source = "uf")
  AddressDetails toDomain(CEPResponse cepResponse);
}
