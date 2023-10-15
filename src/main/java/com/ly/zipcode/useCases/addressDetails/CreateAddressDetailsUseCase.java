package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.domains.addressDetails.AddressDetailsDomainMapper;
import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModelMapper;
import com.ly.zipcode.infra.repositories.addressDetails.read.AddressDetailsReadDAO;
import com.ly.zipcode.infra.repositories.addressDetails.write.AddressDetailsWriteDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateAddressDetailsUseCase {
  private final AddressDetailsWriteDAO addressDetailsWriteDAO;
  private final AddressDetailsReadDAO addressDetailsReadDAORead;
  private final AddressDetailsDomainMapper addressDetailsDomainMapper;
  private final AddressDetailsDataModelMapper addressDetailsDataModelMapper;

  public AddressDetails execute(AddressDetails addressDetails) {
    var id = addressDetailsWriteDAO.create(addressDetailsDataModelMapper.toDataModel(addressDetails));
    return addressDetailsDomainMapper.toDomain(addressDetailsReadDAORead.findById(id));
  }
}
