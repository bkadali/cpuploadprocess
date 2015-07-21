package com.cpuploadprocess.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.cpuploadprocess.model.CouponDetails;
import com.cpuploadprocess.model.CouponMapper;
import com.cpuploadprocess.model.CustomerDetails;
import com.mysql.jdbc.PreparedStatement;

@Repository
public class MyDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void create(CouponDetails cp) {
		String SQL = "insert into CPUPLOAD.CouponData (Merchant,BussinessType,Address,zipcode,longitude,lat, couponInfo,couponimage) values (?,?,?,?,?,?,?,?)";

		try {
			
			LobHandler lobHandler = new DefaultLobHandler(); 
			
			jdbcTemplate.update(SQL, new Object[]{cp.getMerchant(), cp.getCouponType(),
					cp.getAddress(), cp.getZipcode(), cp.getLng(), cp.getLat(), cp.getCouponInfo(),new SqlLobValue(cp.getImage(), lobHandler)});
		} catch (Exception e) {
			System.out.println("test" + e.getMessage());
			e.printStackTrace();
		}
		return;
	}
	
	/**
     * save
     */
    public void save(final CouponDetails cp) {
    	final String SQL = "insert into CPUPLOAD.CouponData (Merchant,BussinessType,Address,zipcode,longitude,lat, couponInfo,couponimage) values (?,?,?,?,?,?,?,?)";

        try {
            synchronized(this) {
                jdbcTemplate.update(new PreparedStatementCreator() {
 
                    public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        java.sql.PreparedStatement statement = con.prepareStatement(SQL);
                        statement.setString(1, cp.getMerchant());
                        statement.setString(2, cp.getCouponType());
                        statement.setString(3,cp.getAddress());
                        statement.setString(4, cp.getZipcode());
                        statement.setString(5, cp.getLng());
                        statement.setString(6, cp.getLat());
                        statement.setString(7,cp.getCouponInfo());
                        statement.setBytes(8, cp.getImage());

                        
                        return statement;
                    }
                });
            }
        } catch (Exception ex) {
        	System.out.println("test" + ex.getMessage());
            ex.printStackTrace();
        }
    }
 

	/**
     * save customer
     */
    public void save(final CustomerDetails cp) {
    	final String SQL = "insert into Customer(CustomerEmail,CustomerName,Address,zipcode,phonenumber,directReferal1,directReferal2)"
    			+ " values (?,?,?,?,?,?,(select col1 from (select directReferal1 as col1 from Customer where CustomerEmail=?)as subquery))";

        try {
            synchronized(this) {
                jdbcTemplate.update(new PreparedStatementCreator() {
 
                    public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        java.sql.PreparedStatement statement = con.prepareStatement(SQL);
                        statement.setString(1, cp.getEmail());
                        statement.setString(2, cp.getCustomerName());
                        statement.setString(3,cp.getAddress());
                        statement.setString(4, cp.getZipcode());
                        statement.setString(5, cp.getPhone());
                        statement.setString(6, cp.getReferalId());
                        statement.setString(7,cp.getReferalId());
                       // statement.setBytes(8, cp.getImage());

                        
                        return statement;
                    }
                });
            }
        } catch (Exception ex) {
        	System.out.println("test" + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
 
	public List<CouponDetails> getCouponDetails(String zipCode,
			String businessType) {
		String SQL = "select * from CPUPLOAD.CouponData where zipcode = '"+zipCode+"'";// and
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
			cd.setImage((byte[]) row.get("couponimage"));
			cdList.add(cd);
		}

		return cdList;
	}

	
	public void redemCalculation(String customerEmail, String businessType, String coupon) {
		
	}
	
}