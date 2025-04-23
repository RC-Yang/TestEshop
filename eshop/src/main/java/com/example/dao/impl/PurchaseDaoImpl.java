package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.PurcahseDao;

@Repository
public class PurchaseDaoImpl implements PurcahseDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Object[] queryPurcahseProduct(Integer prodId) {
		String sql = "SELECT p.prod_name,p.prod_price FROM join product p "
				+" where uc.prod_id=?";
		
		List<Object[]> resultList = jdbcTemplate.query(sql,new Object[] {prodId} ,
			    (rs, rowNum) -> {
			    	Object[] obj = new Object[5];
		        	obj[0]=rs.getString(1);
		        	obj[1]=rs.getInt(2);
		            return obj;
			    });
		return resultList.get(0);
	}

}
