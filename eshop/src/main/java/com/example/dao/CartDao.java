package com.example.dao;

import java.util.List;

import com.example.pojo.entity.UserCart;

public interface CartDao {
	public int addToCart(UserCart usercart);
	public List<Object[]> queryCart(String loginId);
	public Object[] queryCart(String loginId,Integer prodId);
	public int updateCart(UserCart usercart);
}
