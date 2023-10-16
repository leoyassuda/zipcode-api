package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.infra.repositories.addressDetails.write.AddressDetailsWriteDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteAddressDetailsUseCase {
  private final AddressDetailsWriteDAO addressDetailsWriteDAO;

  public void execute(UUID id) {
    addressDetailsWriteDAO.delete(id);
  }
}
