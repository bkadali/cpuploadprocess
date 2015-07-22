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
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

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
    		@RequestParam("couponInfo") String couponInfo,
    		@RequestParam("expireDate") String expireDate,
    		@RequestParam("name") String name, 
    		@RequestParam("facevalue") String facevalue,
    		@RequestParam("discount") String discount,
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
            	System.out.println("found file" + file.getBytes());
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                CouponDetails cp = new CouponDetails();
                cp.setCouponId("212");
                cp.setAddress(address);
                cp.setZipcode(zipCode);
                cp.setCouponType(agentName);
                cp.setMerchant(companyName);
                cp.setCouponInfo(couponInfo);
                cp.setImage(bytes);
                cp.setDiscount(discount);
                cp.setFacevalue(facevalue);
               
                stream.close();
                System.out.println("Calling google api");
                GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD7-9GYJ3UEnRkEHaK3DNdrDY5zgpQiYW4");
                GeocodingResult[] results =  GeocodingApi.geocode(context,
                   cp.getAddress()+ ", "+cp.getZipcode()).await();
                System.out.println("Got the results Latititue  " + results[0].geometry.location.lat);
                System.out.println("Got the results Longitude " + results[0].geometry.location.lng);
                cp.setLat(Double.toString(results[0].geometry.location.lat));
                cp.setLng(Double.toString(results[0].geometry.location.lng));
                dao.save(cp);
                dao.getCouponDetails("60008", "erte");
                
                
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
