package com.ly.zipcode.controllers.addressDetails;

import com.ly.zipcode.controllers.IController;
import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.useCases.addressDetails.AddressDetailsUseCase;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/addressDetails")
public class AddressDetailsController implements IController<AddressDetails> {

  private final AddressDetailsUseCase addressDetailsUseCase;

  @Override
  public ResponseEntity<AddressDetails> findById(UUID id) {
    return ResponseEntity.ok(addressDetailsUseCase.findById(id));
  }

  @GetMapping("/zipcode/{zipcode}")
  public ResponseEntity<AddressDetails> findByZipCode(@PathVariable @Pattern(regexp = "\\d{5}-\\d{3}", message = "error.api.addressDetails.id.invalid") String zipcode) {
    return ResponseEntity.ok(addressDetailsUseCase.findByZipCode(zipcode));
  }

  @Override
  public ResponseEntity<List<AddressDetails>> findAll(int pageSize, int page) {
    return ResponseEntity.ok().body(addressDetailsUseCase.findAll(pageSize, page));
  }

  @Override
  public ResponseEntity<AddressDetails> create(AddressDetails addressDetails) {
    return ResponseEntity.status(HttpStatus.CREATED).body(addressDetailsUseCase.create(addressDetails));
  }

  @Override
  public ResponseEntity<AddressDetails> update(UUID id, AddressDetails addressDetails) {
    return ResponseEntity.ok(addressDetailsUseCase.update(id, addressDetails));
  }

  @Override
  public ResponseEntity<Void> delete(UUID id) {
    addressDetailsUseCase.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
