package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.domains.addressDetails.AddressDetailsDomainMapper;
import com.ly.zipcode.infra.repositories.addressDetails.read.AddressDetailsReadDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindAllAddressDetailsUseCase {
  private final AddressDetailsReadDAO addressDetailsReadDAO;
  private final AddressDetailsDomainMapper addressDetailsDomainMapper;

  public List<AddressDetails> execute(int pageSize, int size) {
    return addressDetailsDomainMapper.toDomain(addressDetailsReadDAO.findAll(pageSize, size));
  }
}
