package com.example.service.impl;

import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ProductDao;
import com.example.dao.ProductRepository;
import com.example.pojo.entity.Product;
import com.example.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(transactionManager = "hibernateTxManager")
	public List<Object[]> queryAllProduct() {
		
		return productDao.queryAllProduct();
	}

	@Transactional(transactionManager = "hibernateTxManager")
	public Object[] queryProductById(Integer id) {
		return productDao.queryProductById(id);
	}

	@Override
	@Transactional(transactionManager = "hibernateTxManager")
	public Blob findProdImageByProdId(Integer prodId) {
		return productRepository.findProdImageByProdId(prodId);
	}
}
