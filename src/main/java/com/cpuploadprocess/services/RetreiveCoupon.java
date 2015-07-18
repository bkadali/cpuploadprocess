package com.cpuploadprocess.services;

import java.util.List;

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
	public List<CouponDetails> getCoupon(@PathVariable String businessType, @PathVariable String zipcode) {
		
		List<CouponDetails> dtls = dao.getCouponDetails(zipcode, businessType);;
		
		
		return dtls;
	}
}