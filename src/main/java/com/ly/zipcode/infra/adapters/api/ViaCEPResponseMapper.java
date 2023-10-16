package com.ly.zipcode.infra.adapters.api;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ViaCEPResponseMapper {

  @Mapping(target = "cep", source = "zipcode")
  @Mapping(target = "logradouro", source = "address")
  @Mapping(target = "complemento", source = "additionalAddress")
  @Mapping(target = "bairro", source = "neighborhood")
  @Mapping(target = "localidade", source = "city")
  @Mapping(target = "uf", source = "state")
  CEPResponse mapToCEPResponse(AddressDetails addressDetails);

}
