package com.ly.zipcode.infra.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfigRead {

  @Primary
  @Bean(name = "readDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.read")
  public DataSource readDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "readJdbcTemplate")
  public JdbcTemplate readJdbcTemplate(@Qualifier("readDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
