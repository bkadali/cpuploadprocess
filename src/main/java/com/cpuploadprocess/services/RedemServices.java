package com.cpuploadprocess.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpuploadprocess.dao.MyDao;

@RestController
@RequestMapping("/redeem")
public class RedemServices {

	@Autowired
	MyDao dao;

	/*
	 * method to calculate redeem operation
	 */
	@RequestMapping(value = "/coupon/{couponid}/{email:.+}", method = RequestMethod.GET)
	public void redeemCoupon(@PathVariable String couponid,
			@PathVariable String email) {
		//dao.redemCalculation("", businessType, name);
		System.out.println(" Redeeming Coupon with id "+couponid+" for customer with email "+email);
		dao.redeemCoupon(new Integer(couponid), email);
		//get coupon face value...maxfacevalue - discount
		// get customer e-mail .. set it to 3% of(maxfacevalue - discount) for first user
		// get directrefer1 e-mail... set 
		
		
	}

}
