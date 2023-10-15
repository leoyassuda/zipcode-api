package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.domains.addressDetails.AddressDetailsDomainMapper;
import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModelMapper;
import com.ly.zipcode.infra.repositories.addressDetails.read.AddressDetailsReadDAO;
import com.ly.zipcode.infra.repositories.addressDetails.write.AddressDetailsWriteDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateAddressDetailsUseCase {
  private final AddressDetailsWriteDAO addressDetailsWriteDAO;
  private final AddressDetailsReadDAO addressDetailsReadDAORead;
  private final AddressDetailsDataModelMapper addressDetailsDataModelMapper;
  private final AddressDetailsDomainMapper addressDetailsDomainMapper;

  public AddressDetails execute(UUID id, AddressDetails addressDetails) {
    addressDetailsWriteDAO.update(id, addressDetailsDataModelMapper.toDataModel(addressDetails));
    return addressDetailsDomainMapper.toDomain(addressDetailsReadDAORead.findById(id));
  }
}
