package com.example.dao;

import java.sql.Blob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.pojo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("SELECT p.prodImage FROM Product p WHERE p.prodId = :prodId")
	Blob findProdImageByProdId(@Param("prodId") Integer prodId);
}
