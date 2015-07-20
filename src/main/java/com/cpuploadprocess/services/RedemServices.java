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
	@RequestMapping(value = "/coupon/{businessType}/{name}", method = RequestMethod.GET)
	public void redeemCoupon(@PathVariable String businessType,
			@PathVariable String name) {
		dao.redemCalculation("", businessType, name);
	}

}
