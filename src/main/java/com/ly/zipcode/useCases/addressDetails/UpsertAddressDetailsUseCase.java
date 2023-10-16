package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.infra.repositories.addressDetails.read.AddressDetailsReadDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpsertAddressDetailsUseCase {
  private final FindByIdAddressDetailsUseCase findByIdAddressDetailsUseCase;
  private final CreateAddressDetailsUseCase createAddressDetailsUseCase;
  private final UpdateAddressDetailsUseCase updateAddressDetailsUseCase;
  private final AddressDetailsReadDAO addressDetailsReadDAO;

  public AddressDetails execute(AddressDetails addressDetails) {
    if (addressDetails.getId() == null) {
      try {
        var addressDetailsInDb = addressDetailsReadDAO.findByZipcode(addressDetails.getZipcode());
        addressDetails.setId(addressDetailsInDb.getId());
        return updateAddressDetailsUseCase.execute(addressDetails.getId(), addressDetails);
      } catch (EmptyResultDataAccessException e) {
        return createAddressDetailsUseCase.execute(addressDetails);
      }
    } else {
      return findByIdAddressDetailsUseCase.execute(addressDetails.getId());
    }
  }
}
