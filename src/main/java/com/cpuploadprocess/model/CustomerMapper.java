package com.cpuploadprocess.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<CustomerDetails> {
	public CustomerDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerDetails cdetails = new CustomerDetails();

		return cdetails;
	}

	public class CustomerResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException {
			CustomerDetails coupon = new CustomerDetails();
			coupon.setCustomerName(rs.getString(1));
			coupon.setEmail(rs.getString(6));
			return coupon;
		}

	}
}