package com.store.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.store.entities.StoreDetail;

@Service
public class StoreDetailServiceImpl implements StoreDetailService{

	@Override
	@Transactional
	public String saveStoreDetails(StoreDetail storeDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String updateStoreDetails(StoreDetail storeDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<StoreDetail> retrieveStoreDetails() {
		// TODO Auto-generated method stub
		return null;
	}


}
