package com.ly.zipcode.useCases.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AddressDetailsUseCase implements IUseCase<AddressDetails> {

  private final FindByIdAddressDetailsUseCase findByIdAddressDetailsUseCase;
  private final FindAllAddressDetailsUseCase findAllAddressDetailsUseCase;
  private final CreateAddressDetailsUseCase createAddressDetailsUseCase;
  private final UpdateAddressDetailsUseCase updateAddressDetailsUseCase;
  private final DeleteAddressDetailsUseCase deleteAddressDetailsUseCase;
  private final FindByZipCodeAddressDetailsUseCase findByZipCodeAddressDetailsUseCase;

  @Cacheable(value = "addressDetailsCache", key = "#id")
  @Override
  public AddressDetails findById(UUID id) {
    return findByIdAddressDetailsUseCase.execute(id);
  }

  @Cacheable(value = "addressDetailsCache", key = "#zipcode")
  public AddressDetails findByZipCode(String zipcode) {
    return findByZipCodeAddressDetailsUseCase.execute(zipcode);
  }

  @Cacheable(value = "addressDetailsCache", key = "'allAddressDetails-' + #pageSize + '-' + #size")
  @Override
  public List<AddressDetails> findAll(int pageSize, int size) {
    return findAllAddressDetailsUseCase.execute(pageSize, size);
  }

  @CacheEvict(value = "addressDetailsCache", allEntries = true)
  @Override
  public AddressDetails create(AddressDetails addressDetails) {
    return createAddressDetailsUseCase.execute(addressDetails);
  }

  @CachePut(value = "addressDetailsCache", key = "#id")
  @Override
  public AddressDetails update(UUID id, AddressDetails addressDetails) {
    return updateAddressDetailsUseCase.execute(id, addressDetails);
  }

  @Override
  public void delete(UUID id) {
    deleteAddressDetailsUseCase.execute(id);
  }

}
