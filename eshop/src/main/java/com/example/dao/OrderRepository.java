package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.pojo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Modifying
    @Transactional
    @Query("UPDATE Order o SET o.state = :state WHERE o.ordNum = :ordNum")
    void updateOrderState(@Param("ordNum") String ordNum, @Param("state") String state);
	
	List<Order> findByCustomer_CustId(Integer custId);
}
