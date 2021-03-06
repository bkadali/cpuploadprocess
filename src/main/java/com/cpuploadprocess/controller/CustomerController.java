package com.cpuploadprocess.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cpuploadprocess.dao.MyDao;
import com.cpuploadprocess.model.CouponDetails;
import com.cpuploadprocess.model.CustomerDetails;

@Controller
public class CustomerController {
	
	@Autowired
	MyDao dao;
	
    
    @RequestMapping(value="/customer", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    
    @RequestMapping(value="/customer", method=RequestMethod.POST)
    public @ResponseBody String customerLink(
    		@RequestParam("customerName") String customerName,
    		@RequestParam("address") String address,
    		@RequestParam("zipCode") String zipCode,
    		@RequestParam("phoneNumber") String phoneNumber,
    		@RequestParam("email") String email,
    		@RequestParam("referalId") String referalId){
        if (!customerName.isEmpty()) {
            try {
            	System.out.println("found file");
               System.out.println(" customer name is "+customerName);
               System.out.println(" customer id is "+referalId);
                CustomerDetails custdetails = new CustomerDetails();
                custdetails.setAddress(address);
                custdetails.setCustomerName(customerName);
                custdetails.setZipcode(zipCode);
                custdetails.setPhone(phoneNumber);
                custdetails.setEmail(email);
                custdetails.setReferalId(referalId);
                dao.save(custdetails);
                //dao.getCouponDetails("60008");
                
                
                return "You successfully uploaded " + customerName + "!";
            } catch (Exception e) {
                return "You failed to upload " + customerName + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + customerName + " because the file was empty.";
        }
    }
}
