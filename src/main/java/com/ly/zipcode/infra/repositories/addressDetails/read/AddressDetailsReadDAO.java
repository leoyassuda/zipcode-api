package com.ly.zipcode.infra.repositories.addressDetails.read;

import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModel;
import com.ly.zipcode.infra.repositories.IReadDAO;
import com.ly.zipcode.infra.repositories.addressDetails.AddressDetailsDataModelRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class AddressDetailsReadDAO implements IReadDAO<AddressDetailsDataModel> {

  private final String SQL_FIND_BY_ID = "select * from address_details where id = ?";
  private final String SQL_FIND_ALL = "select * from address_details limit ? offset ?";

  private final JdbcTemplate readJdbcTemplate;

  @Autowired
  public AddressDetailsReadDAO(@Qualifier("readJdbcTemplate") JdbcTemplate readJdbcTemplate) {
    this.readJdbcTemplate = readJdbcTemplate;
  }

  @Override
  public AddressDetailsDataModel findById(UUID id) {
    log.info("Getting author by ID: {}", id);
    return readJdbcTemplate.queryForObject(SQL_FIND_BY_ID, new AddressDetailsDataModelRowMapper(), id);
  }

  @Override
  public List<AddressDetailsDataModel> findAll(int pageSize, int page) {
    log.info("Getting all authors");
    return readJdbcTemplate.query(SQL_FIND_ALL, new AddressDetailsDataModelRowMapper(), pageSize, page);
  }
}
