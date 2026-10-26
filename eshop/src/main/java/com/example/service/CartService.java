package com.example.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
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
	
	@Transactional(transactionManager = "jdbcTxManager")
	public int addToCart(Integer userId,Integer prodId,Integer quantity) {
	
		int result = 0;

		UserCart usercart = new UserCart();
		
		Product p = new Product();
		p.setProdId(prodId);
		
		User user = new User();
		user.setLoginId(userId.toString());
		
		usercart.setProduct(p);
		usercart.setUser(user);
		usercart.setQuantity(quantity);

		Object[] obj = cartDao.queryCart(userId.toString(), prodId);
		
		if(obj==null) {
			result = cartDao.addToCart(usercart);
		}else {
			quantity=quantity+(Integer)obj[2];
			usercart.setQuantity(quantity);
			
			result = cartDao.updateCart(usercart);
		}	

		return result;
	}
	
	@Transactional(transactionManager = "jdbcTxManager")
	public int removeFromCart(Integer userId, List<Integer> prodIds) {

		int result = 0;
		
		User user = new User();
		user.setLoginId(userId.toString());
		
		for (Integer prodId : prodIds) {

	        Product p = new Product();
	        p.setProdId(prodId);

	        UserCart usercart = new UserCart();
	        usercart.setUser(user);
	        usercart.setProduct(p);

	        result += cartDao.removeFromCart(usercart);
	    }
		return result;
	}
	
	@Transactional(transactionManager = "jdbcTxManager")
	public List<Object[]> queryCart(String loginId) {
		return cartDao.queryCart(loginId);
	}
}
