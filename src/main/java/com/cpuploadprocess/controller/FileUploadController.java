package com.cpuploadprocess.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cpuploadprocess.dao.MyDao;
import com.cpuploadprocess.model.CouponDetails;

@Controller
public class FileUploadController {
	
	@Autowired
	MyDao dao;
	
    
    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("companyName") String companyName,
    		@RequestParam("businessType") String agentName,
    		@RequestParam("address") String address,
    		@RequestParam("zipCode") String zipCode,
    		@RequestParam("expireDate") String expireDate,
    		@RequestParam("name") String name, 
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
            	System.out.println("found file");
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                CouponDetails cp = new CouponDetails();
                cp.setAddress(address);
                cp.setZipcode(zipCode);
                cp.setCouponType(agentName);
                cp.setMerchant(companyName);
                
                //dao.create(cp);
                stream.close();
                
                //dao.getCouponDetails("60008");
                
                
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
