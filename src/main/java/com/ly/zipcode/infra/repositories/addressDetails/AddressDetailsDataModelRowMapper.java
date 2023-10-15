package com.ly.zipcode.infra.repositories.addressDetails;

import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class AddressDetailsDataModelRowMapper implements RowMapper<AddressDetailsDataModel> {

  @Override
  public AddressDetailsDataModel mapRow(ResultSet rs, int rowNum) throws SQLException {
    var authorId = rs.getObject("id", java.util.UUID.class);
    return AddressDetailsDataModel.builder()
        .id(authorId)
        .zipcode(rs.getString("zipcode"))
        .address(rs.getString("address"))
        .additionalAddress(rs.getString("additional_address"))
        .neighborhood(rs.getString("neighborhood"))
        .city(rs.getString("city"))
        .state(rs.getString("state"))
        .ibge(rs.getString("ibge"))
        .gia(rs.getString("gia"))
        .ddd(rs.getString("ddd"))
        .siafi(rs.getString("siafi"))
        .build();
  }
}
