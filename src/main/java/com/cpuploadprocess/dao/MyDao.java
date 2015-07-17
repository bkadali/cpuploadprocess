package com.cpuploadprocess.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cpuploadprocess.model.CouponDetails;
import com.cpuploadprocess.model.CouponMapper;

@Repository
public class MyDao {

	@Autowired
    JdbcTemplate jdbcTemplate;
   
   public void create(CouponDetails cp) {
	      String SQL = "insert into CouponData (couponID,merchant,couponType,address,zipcode,lng,lat) values (?, ?,?,?,?,?,?)";
	      
	     // jdbcTemplate.update( SQL, cp.getCouponId(), cp.getMerchant(),cp.getCouponType(), cp.getAddress(),cp.getZipcode(), cp.getLng(), cp.getLat());

	      return;
	   }

	   public CouponDetails getCouponDetails(String zipCode) {
	      String SQL = "select * from CouponData where zipcode = ?";
	      //CouponDetails student = jdbcTemplate.queryForObject(SQL, 
	       //                 new Object[]{}, new CouponMapper());
	      return null;
	   }

}