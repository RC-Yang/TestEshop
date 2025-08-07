package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.pojo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Modifying
	//@Modifying
	//作用是讓@Query產生非查詢的JPQL，以補強Spring Data JPA預設，非查詢方法，功能上只能用id來作為條件的限制。
    @Transactional
    @Query("UPDATE Order o SET o.state = :state WHERE o.ordNum = :ordNum")
    void updateOrderState(@Param("ordNum") String ordNum, @Param("state") String state);
	
	Page<Order> findByCustomer_CustId(Integer custId, Pageable pageable);
	
	List<Order> findByCustomer_CustId(Integer custId);
	
	@Query("SELECT distinct o.ordNum, o.state FROM Order o")
	List<Object> findStateAndOrderNum();
	
	@Modifying
	@Transactional
	@Query("UPDATE Order o SET o.state = :state WHERE o.ordNum = :ordNum")
	int updateOrderStateByOrdNum(@Param("state") String state, @Param("ordNum") String ordNum);
}
