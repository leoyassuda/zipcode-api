package com.ly.zipcode.infra.repositories.addressDetails.write;

import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModel;
import com.ly.zipcode.infra.repositories.IWriteDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ly.zipcode.utils.TextUtils.camelCaseToSnakeCase;

@Slf4j
@Repository
public class AddressDetailsWriteDAO implements IWriteDAO<AddressDetailsDataModel> {

  private final String SQL_DELETE = "delete from address_details where id = ?";
  private final String SQL_INSERT = "insert into address_details (zipcode, address, additional_address, neighborhood, city, state, ibge, gia, ddd, siafi)" + "values (?,?,?,?,?,?,?,?,?,?)";

  private final JdbcTemplate readJdbcTemplate;

  @Autowired
  public AddressDetailsWriteDAO(@Qualifier("writeJdbcTemplate") JdbcTemplate readJdbcTemplate) {
    this.readJdbcTemplate = readJdbcTemplate;
  }

  @Transactional
  @Override
  public UUID create(AddressDetailsDataModel addressDetailsDataModel) {
    log.info("Create post: {}", addressDetailsDataModel);

    var keyHolder = new GeneratedKeyHolder();
    readJdbcTemplate.update(connection -> {
      var preparedStatement = connection.prepareStatement(SQL_INSERT, new String[]{ "id" });
      preparedStatement.setString(1, addressDetailsDataModel.getZipcode());
      preparedStatement.setString(2, addressDetailsDataModel.getAddress());
      preparedStatement.setString(3, addressDetailsDataModel.getAdditionalAddress());
      preparedStatement.setString(4, addressDetailsDataModel.getNeighborhood());
      preparedStatement.setString(5, addressDetailsDataModel.getCity());
      preparedStatement.setString(6, addressDetailsDataModel.getState());
      preparedStatement.setString(7, addressDetailsDataModel.getIbge());
      preparedStatement.setString(8, addressDetailsDataModel.getGia());
      preparedStatement.setString(9, addressDetailsDataModel.getDdd());
      preparedStatement.setString(10, addressDetailsDataModel.getSiafi());
      return preparedStatement;
    }, keyHolder);

    return keyHolder.getKeyAs(UUID.class);
  }

  @Transactional
  @Override
  public int update(UUID id, AddressDetailsDataModel addressDetailsDataModel) {
    log.info("Update post: {}", id);
    var updateSql = "update address_details set ";

    List<String> setClauses = new ArrayList<>();
    List<Object> setValues = new ArrayList<>();

    var fields = addressDetailsDataModel.getClass().getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      try {
        String fieldName = camelCaseToSnakeCase(field.getName());
        if (!fieldName.equals("id")) {
          var value = field.get(addressDetailsDataModel);
          if (value != null) {
            setClauses.add(fieldName + " = ?");
            setValues.add(value);
          }
        }
      } catch (IllegalAccessException e) {
        log.error(e.getMessage());
      }
    }

    if (setClauses.isEmpty()) {
      return 0;
    }

    updateSql += String.join(", ", setClauses) + " where id = ?";
    setValues.add(id);

    return readJdbcTemplate.update(updateSql, setValues.toArray());
  }

  @Transactional
  @Override
  public void delete(UUID id) {
    log.info("Delete post: {}", id);
    readJdbcTemplate.update(SQL_DELETE, id);
  }
}
