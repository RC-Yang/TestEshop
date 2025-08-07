package com.example.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pojo.entity.Customer;

//Spring Data JPA，跨表查詢，是透過Entity的關聯對映來做到
//那麼如果真的做跨表查詢，關連到的表格，該表可以只查詢部分想要的欄位嗎？
//如果不用@Query，跨表查詢時，就是只能查出關聯表的全部欄位了
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Optional<Customer> findByLoginId(String loginId);
}