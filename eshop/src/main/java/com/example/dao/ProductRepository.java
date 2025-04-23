package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pojo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
