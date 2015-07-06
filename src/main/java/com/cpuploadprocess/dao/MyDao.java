package com.cpuploadprocess.dao;

import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyDao {

   private JdbcTemplate jdbcTemplate;

   @Autowired
   public void setDataSource(DataSource dataSource) {
       this.jdbcTemplate = new JdbcTemplate(dataSource);
   }

   public UUID getId() {
      String sql = "SELECT id FROM my_schema.my_table WHERE my_ip_address=inet('10.1.1.1')";
       UUID id = jdbcTemplate.queryForObject(sql, UUID.class);
      return id;
   }
}