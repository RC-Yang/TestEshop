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
        return jdbcTemplate.update(sql, usercart.getUser().getLoginId(),
        		usercart.getProduct().getProdId(),usercart.getQuantity()==null?1:usercart.getQuantity()+1);
	}

	@Override
	public List<Object[]> queryCart(String loginId) {
		String sql = "SELECT p.prod_name,p.prod_price, uc.quantity,p.prod_id FROM user_cart uc join "
				+ "user u "
				+ "on uc.user_id=u.login_id "
				+ "join product p "
				+ "on uc.prod_id=p.prod_id "
				+" where uc.user_id = ?";
        return jdbcTemplate.query(sql,ps -> ps.setString(1, loginId), (rs, rowNum) -> {
        	Object[] obj = new Object[5];
        	obj[0]=rs.getString(1);
        	obj[1]=rs.getInt(2);
        	obj[2]=rs.getInt(3);
        	obj[3]=rs.getInt(4);
            return obj;
        });
	}
	
	public Object[] queryCart(String loginId,Integer prodId) {
		String sql = "SELECT p.prod_name,p.prod_price, uc.quantity,p.prod_id FROM user_cart uc join "
				+ "user u "
				+ "on uc.user_id=u.login_id "
				+ "join product p "
				+ "on uc.prod_id=p.prod_id "
				+" where uc.user_id = ? and uc.prod_id=?";
		List<Object[]> resultList = jdbcTemplate.query(sql,new Object[] {loginId,prodId} ,
	    (rs, rowNum) -> {
	    	Object[] obj = new Object[5];
        	obj[0]=rs.getString(1);
        	obj[1]=rs.getInt(2);
        	obj[2]=rs.getInt(3);
        	obj[3]=rs.getInt(4);
            return obj;
	    });
		
		if (!resultList.isEmpty()) {
		    Object[] result = resultList.get(0);
		    return result;
		} else {
		    return null;
		}
	}
	
	public int updateCart(UserCart usercart) {
		String sql = "UPDATE user_cart SET quantity = quantity + 1"
				+ " WHERE user_id = ? AND prod_id = ?";
		
		int rows = jdbcTemplate.update(sql, new Object[] {usercart.getUser().getLoginId(),
				usercart.getProduct().getProdId()});
		
		return rows;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
