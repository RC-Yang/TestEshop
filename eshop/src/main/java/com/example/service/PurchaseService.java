package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.PurcahseDao;

@Service
public class PurchaseService {
	
	private PurcahseDao purcahseDao;

	@Transactional(transactionManager = "jdbcTxManager")
	public Object[] queryPurcahseProduct(Integer prodId) {
		return purcahseDao.queryPurcahseProduct(prodId);
	}
}
