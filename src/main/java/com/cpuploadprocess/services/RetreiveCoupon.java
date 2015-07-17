package com.cpuploadprocess.services;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpuploadprocess.dao.MyDao;
import com.cpuploadprocess.model.CouponDetails;

@RestController
@RequestMapping("/retreive")
public class RetreiveCoupon {

	@Autowired
	MyDao dao;

	@RequestMapping(value = "/coupon/{businessType}/{zipcode}", method = RequestMethod.GET)
	public CouponDetails getCoupon(@PathVariable String businessType, @PathVariable String zipcode) {
		//dao.getCouponDetails(zipcode, businessType);
		CouponDetails dtls = new CouponDetails();
		dtls.setAddress("123 test");
		dtls.setCouponType("Restuarent");
		dtls.setZipcode("60008");
		dtls.setLat("-88.042236");
		dtls.setLng("42.050123");
		
		return dtls;
	}
	
	
	@RequestMapping(value = "/ret/data", method = RequestMethod.GET)
	public CouponDetails getDataDtls(@RequestParam(value="test") String test) {
		//dao.getCouponDetails(test);
		return null;//dao.getCouponDetails(test);
	}
}