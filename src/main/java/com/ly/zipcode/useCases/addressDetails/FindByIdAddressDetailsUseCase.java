package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.domains.addressDetails.AddressDetailsDomainMapper;
import com.ly.zipcode.infra.repositories.addressDetails.read.AddressDetailsReadDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FindByIdAddressDetailsUseCase {
  private final AddressDetailsReadDAO addressDetailsReadDAO;
  private final AddressDetailsDomainMapper addressDetailsDomainMapper;

  public AddressDetails execute(UUID id) {
    return addressDetailsDomainMapper.toDomain(addressDetailsReadDAO.findById(id));
  }
}
