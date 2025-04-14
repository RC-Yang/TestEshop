package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.CartDao;
import com.example.pojo.entity.UserCart;

@Repository
public class CartDaoImpl implements CartDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int addToCart(UserCart usercart) {
		
		String sql = "INSERT INTO user_cart (user_id, prod_id,quantity) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, usercart.getUser().getId(),
        		usercart.getProduct().getProdId(),usercart.getQuantity()==null?1:usercart.getQuantity()+1);
	}

	@Override
	public List<Object[]> queryCart(String loginId) {
		String sql = "SELECT p.prod_name,p.prod_price, uc.quantity FROM user_cart uc join "
				+ "user u "
				+ "on uc.user_id=u.login_id "
				+ "join product p "
				+ "on uc.prod_id=p.prod_id ";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
        	Object[] obj = new Object[5];
        	obj[0]=rs.getString(1);
        	obj[1]=rs.getInt(2);
        	obj[2]=rs.getInt(3);
            return obj;
        });
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
