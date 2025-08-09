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
		//寫成A JOIN B JOIN C，解析器無法直接確定或預設某種JOIN方式，是因為可能的JOIN方法太多了
		String sql = "SELECT p.prod_name,p.prod_price, uc.quantity,p.prod_id FROM user_cart uc join "
				+ "user u "
				+ "on uc.user_id=u.login_id "
				+ "join product p "
				+ "on uc.prod_id=p.prod_id "
				+" where uc.user_id = ? and uc.prod_id=?";
		List<Object[]> resultList = jdbcTemplate.query(sql,new Object[] {loginId,prodId} ,
		//傳統JDBC，ResultSet邏輯上是一整個查詢結果，而指標已指向其中一筆查詢結果，那要用甚麼來表示該查詢結果？
		//就是ResultSet，其會不斷指向下一筆查詢到的資料
		//mapRow需要接收resultSet作為參數？他不是一次只處理一筆資料？
		//這是因為resultSet，就是當前那筆資料。
		//rowNum參數沒有用到，那還要寫進Lambda參數裡面？這是因為要用方法簽名，對應真正的方法
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
		//int rows = jdbcTemplate.update(sql, usercart.getUser().getLoginId(),
		//usercart.getProduct().getProdId());
		//這也可以

		return rows;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
