package com.ly.zipcode.domains.addressDetails;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDetails implements Serializable {
  @Serial
  private static final long serialVersionUID = 1173227188643206849L;

  private UUID id;

  @Pattern(regexp = "\\d{5}-\\d{3}", message = "error.api.addressDetails.id.invalid")
  @NotBlank(message = "addressDetails.zipcode.notEmpty")
  private String zipcode;

  @NotBlank(message = "addressDetails.address.notEmpty")
  private String address;

  private String additionalAddress;

  @NotBlank(message = "addressDetails.neighborhood.notEmpty")
  private String neighborhood;

  @NotBlank(message = "addressDetails.city.notEmpty")
  private String city;

  @NotBlank(message = "addressDetails.state.notEmpty")
  private String state;

  @Pattern(regexp = "\\d{7}", message = "error.api.addressDetails.ibge.invalid")
  private String ibge;

  @Pattern(regexp = "[A-Z0-9]+", message = "error.api.addressDetails.gia.invalid")
  private String gia;

  @Pattern(regexp = "\\d{2}", message = "error.api.addressDetails.ddd.invalid")
  private String ddd;

  @Pattern(regexp = "\\d{4}", message = "error.api.addressDetails.siafi.invalid")
  private String siafi;
}
