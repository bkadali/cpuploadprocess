package com.cpuploadprocess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpuploadprocess.dao.MyDao;
import com.cpuploadprocess.model.CouponDetails;

@RestController
@RequestMapping("/service/greeting")
public class RetreiveCoupon {

	@Autowired
	MyDao dao;

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public CouponDetails getGreeting(@PathVariable String name) {
		dao.getCouponDetails(name);
		return dao.getCouponDetails(name);
	}
}