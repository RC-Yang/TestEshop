package com.example.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dao.ProductDao;
import com.example.pojo.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Object[]> queryAllProduct() {
		String hql = "select p.prodId,p.prodName,p.prodPrice FROM Product p";
		List<Object[]> products = sessionFactory.getCurrentSession().createQuery(hql)
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
