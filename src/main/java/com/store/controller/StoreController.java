package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.entities.StoreDetail;
import com.store.entities.Users;
import com.store.service.StoreDetailService;

@CrossOrigin
@RestController
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	StoreDetailService storeDetailService;
	
	
	@PostMapping("/saveStoreDtl")
	public String saveStoreDetails(@RequestBody StoreDetail storeDetail) throws Exception{
		
		return storeDetailService.saveStoreDetails(storeDetail);
		
	}
	@PutMapping("/updateStoreDtl")
	public String updateStoreDetails(@RequestBody StoreDetail storeDetail) throws Exception{
		
		return storeDetailService.updateStoreDetails(storeDetail);
		
	}
	@GetMapping("/retrieveStoreDtl")
	public List<StoreDetail> retrieveStoreDetails() throws Exception{
		
		return storeDetailService.retrieveStoreDetails();
		
	}
	
}
