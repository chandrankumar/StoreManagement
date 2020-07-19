package com.store.service;

import java.util.List;

import com.store.entities.StoreDetail;
import com.store.entities.Users;

public interface StoreDetailService {

	public String saveStoreDetails(StoreDetail storeDetail);

	public String updateStoreDetails(StoreDetail storeDetail);

	public List<StoreDetail> retrieveStoreDetails();
}
