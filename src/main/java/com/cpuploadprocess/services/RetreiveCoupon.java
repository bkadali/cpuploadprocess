package com.cpuploadprocess.services;

import java.io.IOException;
import java.util.List;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpuploadprocess.dao.MyDao;
import com.cpuploadprocess.model.CouponDetails;

@RestController
@RequestMapping("/retreive")
public class RetreiveCoupon {

	@Autowired
	MyDao dao;

	/*
	 * Method to get coupon details
	 * http://localhost:8080/coupon/image/grocery/5645645
	 */
	@RequestMapping(value = "/coupon/{businessType}/{zipcode}", method = RequestMethod.GET)
	public List<CouponDetails> getCoupon(@PathVariable String businessType, @PathVariable String zipcode) {
		System.out.println(" coupon GET for "+ zipcode+" for business type "+businessType);
		List<CouponDetails> dtls = dao.getCouponDetails(zipcode, businessType);
		
		return dtls;
	}
	
	/*
	 * Method to get coupon image
	 * http://localhost:8080/retreive/image/grocery/5645645
	 */
	@RequestMapping(value = "/image/{businessType}/{zipcode}", method = RequestMethod.GET)
	public void getImage(@PathVariable String businessType, @PathVariable String zipcode,HttpServletResponse response,HttpServletRequest request) {
		System.out.println(" coupon GET for "+ zipcode+" for business type "+businessType);
		List<CouponDetails> dtls = dao.getCouponDetails(zipcode, businessType);
		 response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		    try {
				response.getOutputStream().write(dtls.get(0).getImage());
			    response.getOutputStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	
	}
}