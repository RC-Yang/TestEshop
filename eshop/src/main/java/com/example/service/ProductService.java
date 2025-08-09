package com.example.service;

import java.sql.Blob;
import java.util.List;

import com.example.pojo.entity.Product;

public interface ProductService {
	public  List<Object[]> queryAllProduct();
	public Object[] queryProductById(Integer id);
	public Blob findProdImageByProdId(Integer prodId);
}
