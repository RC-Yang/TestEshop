package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Object[]> queryAllProduct() {
		
		return productDao.queryAllProduct();
	}

	public Object[] queryProductById(Integer id) {
		return productDao.queryProductById(id);
	}

	@Override
	public byte[] findProdImageByProdId(Integer prodId) {
		return productRepository.findProdImageByProdId(prodId);
	}
}
