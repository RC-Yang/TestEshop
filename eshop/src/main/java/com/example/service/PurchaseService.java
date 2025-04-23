package com.example.service;

import org.springframework.stereotype.Service;

import com.example.dao.PurcahseDao;

@Service
public class PurchaseService {
	
	private PurcahseDao purcahseDao;

	public Object[] queryPurcahseProduct(Integer prodId) {
		return purcahseDao.queryPurcahseProduct(prodId);
	}
}
