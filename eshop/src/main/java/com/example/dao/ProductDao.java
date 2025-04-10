package com.example.dao;

import java.util.List;

import com.example.pojo.entity.Product;

public interface ProductDao {
	public List<Object[]> queryAllProduct();
	public Product queryProductById(Integer id);
	public List<Product> queryProductByTypeId(Integer typeId);
}
