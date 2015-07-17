package com.cpuploadprocess.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class CouponMapper implements RowMapper<CouponDetails> {
	public CouponDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		CouponDetails cdetails = new CouponDetails();

		return cdetails;
	}

	public class CouponResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException {
			CouponDetails coupon = new CouponDetails();
			coupon.setMerchant(rs.getString(1));
			coupon.setLat(rs.getString(6));
			coupon.setLng(rs.getString(5));
			return coupon;
		}

	}
}