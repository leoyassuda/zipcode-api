package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.domains.addressDetails.AddressDetailsDomainMapper;
import com.ly.zipcode.infra.adapters.api.ViaCEPClient;
import com.ly.zipcode.infra.adapters.api.ViaCEPResponseMapper;
import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModelMapper;
import com.ly.zipcode.infra.repositories.addressDetails.read.AddressDetailsReadDAO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FindByZipCodeAddressDetailsUseCase {
  private final UpsertAddressDetailsUseCase upsertAddressDetailsUseCase;

  private final AddressDetailsReadDAO addressDetailsReadDAO;

  private final AddressDetailsDomainMapper addressDetailsDomainMapper;

  private final AddressDetailsDataModelMapper addressDetailsDataModelMapper;

  private final ViaCEPResponseMapper viaCEPResponseMapper;

  private final ViaCEPClient viaCep;

  @CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "fallbackFindByZipcode")
  public AddressDetails execute(String zipcode) {
    var addressDetails = addressDetailsDomainMapper.toDomain(viaCep.getAddressDetailsByZipcode(zipcode));
    return upsertAddressDetailsUseCase.execute(addressDetails);
  }

  private AddressDetails fallbackFindByZipcode(String zipcode, Exception ex) {
    log.error("Got an error, executing fallbackFindByZipcode and returning from database");
    return addressDetailsDomainMapper.toDomain(addressDetailsReadDAO.findByZipcode(zipcode));
  }

}
