package com.ly.zipcode.infra.repositories.addressDetails.write;

import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModel;
import com.ly.zipcode.utils.AddressUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddressDetailsWriteDAOTest {

  @Mock
  JdbcTemplate jdbcTemplate;

  @Mock
  DataSource dataSource;

  @Mock
  KeyHolder keyHolder;

  @InjectMocks
  AddressDetailsWriteDAO addressDetailsWriteDAO;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenCreate_thenReturnValidUUID() {
    var addressDetailsDataModel = AddressUtils.generateAddress(AddressDetailsDataModel.class);

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class)))
        .thenAnswer(invocation -> {
          keyHolder.getKeyList().add(Collections.singletonMap("id", UUID.randomUUID()));
          Object[] args = invocation.getArguments();
          PreparedStatementCreator psc = (PreparedStatementCreator) args[0];
          GeneratedKeyHolder kh = (GeneratedKeyHolder) args[1];
          return 1;
        });

    addressDetailsWriteDAO.create(addressDetailsDataModel);
    assertNotNull(keyHolder.getKeyAs(UUID.class));
  }

  @Test
  void whenUpdate_thenReturnValidRowNumber() {
    var addressDetailsDataModel = AddressUtils.generateAddress(AddressDetailsDataModel.class);
    assert addressDetailsDataModel != null;
    var id = addressDetailsDataModel.getId();
    addressDetailsDataModel.setId(null);

    when(jdbcTemplate.update(
        eq("update address_details set zipcode = ?, address = ?, additional_address = ?, neighborhood = ?, city = ?, state = ?, ibge = ?, gia = ?, ddd = ?, siafi = ? where id = ?"),
        eq(addressDetailsDataModel.getZipcode()),
        eq(addressDetailsDataModel.getAddress()),
        eq(addressDetailsDataModel.getAdditionalAddress()),
        eq(addressDetailsDataModel.getNeighborhood()),
        eq(addressDetailsDataModel.getCity()),
        eq(addressDetailsDataModel.getState()),
        eq(addressDetailsDataModel.getIbge()),
        eq(addressDetailsDataModel.getGia()),
        eq(addressDetailsDataModel.getDdd()),
        eq(addressDetailsDataModel.getSiafi()),
        Mockito.any(UUID.class)))
        .thenReturn(1);

    assertEquals(1, addressDetailsWriteDAO.update(id, addressDetailsDataModel));
  }

  @Test
  void whenDelete_thenVerifyCallOnce() {
    var id = UUID.randomUUID();
    addressDetailsWriteDAO.delete(id);
    verify(jdbcTemplate, times(1)).update("delete from address_details where id = ?", id);
  }
}