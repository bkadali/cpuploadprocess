package com.cpuploadprocess.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpuploadprocess.dao.MyDao;
import com.cpuploadprocess.model.CouponDetails;

@RestController
@RequestMapping("/rewards")
public class UserRewardServices {
	
	@Autowired
	MyDao dao;

	/*
	 * method to calculate redeem operation
	 */
	@RequestMapping(value = "/gained/{name}", method = RequestMethod.GET)
	public List<CouponDetails> redeemCoupon(@PathVariable String businessType,
			@PathVariable String name) {
		dao.calculatedReward(name);
		return null;
	}

}
