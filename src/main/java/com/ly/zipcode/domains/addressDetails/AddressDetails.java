package com.ly.zipcode.domains.addressDetails;

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
  private String zipcode;

  private String address;

  private String additionalAddress;

  private String neighborhood;

  private String city;

  private String state;

  private String ibge;

  private String gia;

  private String ddd;

  private String siafi;

}
