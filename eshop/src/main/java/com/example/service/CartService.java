package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CartDao;
import com.example.dao.ProductDao;
import com.example.dao.UserDao;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.User;
import com.example.pojo.entity.UserCart;

@Service
public class CartService {
	@Autowired
	CartDao cartDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	UserDao userDao;
	
	@Transactional
	public int addToCart(Integer userId,Integer prodId) {
	
		int result = 0;

		Object[] obj = productDao.queryProductById(prodId);
		
		if(obj!=null) {
			UserCart usercart = new UserCart();
			
			Product p = new Product();
			p.setProdId(prodId);
			
			User user = new User();
			user.setId(userId.toString());
			
			usercart.setProduct(p);
			usercart.setUser(user);
			
			result = cartDao.addToCart(usercart);
		}
		return result;
	}
	
	public List<Object[]> queryCart(String loginId) {
		return cartDao.queryCart(loginId);
	}
}
