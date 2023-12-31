package com.ly.zipcode.infra.adapters.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "viaCEP", url = "https://viacep.com.br/ws")
public interface ViaCEPClient {

  @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json", consumes = "application/json")
  CEPResponse getAddressDetailsByZipcode(@PathVariable String cep);
}
