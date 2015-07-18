package com.cpuploadprocess.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		String SQL = "insert into CPUPLOAD.CouponData (Merchant,BussinessType,Address,zipcode,longitude,lat) values (?,?,?,?,?,?)";

		try {
			jdbcTemplate.update(SQL, cp.getMerchant(), cp.getCouponType(),
					cp.getAddress(), cp.getZipcode(), cp.getLng(), cp.getLat());
		} catch (Exception e) {
			System.out.println("test" + e.getMessage());
			e.printStackTrace();
		}
		return;
	}

	public List<CouponDetails> getCouponDetails(String zipCode,
			String businessType) {
		String SQL = "select * from CPUPLOAD.CouponData where zipcode = '60008'";// and
																					// businessType=
																					// ?";

		List<CouponDetails> cdList = new ArrayList<CouponDetails>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);
		for (Map row : rows) {
			CouponDetails cd = new CouponDetails();
			 cd.setCouponId(String.valueOf(row.get("CouponId")));
			// employee.setName((String)row.get("NAME"));
			// employee.setAge(Integer.parseInt(String.valueOf(row.get("AGE"))));
			cd.setMerchant((String) row.get("Merchant"));
			cd.setAddress((String) row.get("Adress"));
			cd.setZipcode((String) row.get("zipcode"));
			cd.setLng((String) row.get("longitude"));
			cd.setLat((String) row.get("lat"));
			cdList.add(cd);
		}

		return cdList;
	}

}