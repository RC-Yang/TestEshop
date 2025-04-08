package com.example.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.ProductDao;
import com.example.pojo.entity.Product;

public class ProductDaoImpl implements ProductDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Product> queryAllProduct() {
		String hql = "FROM Product";
		List<Product> products = sessionFactory.getCurrentSession().createQuery(hql, Product.class)
								.getResultList();
		return products;
	}

	@Override
	public Product queryProductById(Integer id) {
		String hql = "FROM Product p where p.prodId=:id";
		Product product = sessionFactory.getCurrentSession().createQuery(hql, Product.class)
							.setParameter("id", id).uniqueResult();
		return product;
	}

	@Override
	public List<Product> queryProductByTypeId(Integer typeId) {
		String hql = "FROM Product p where p.prodType.prodTypeId=:typeId";
		List<Product> products = sessionFactory.getCurrentSession().createQuery(hql, Product.class)
				.setParameter("typeId", typeId).getResultList();
		return products;
	}

}
