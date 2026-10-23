package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.dao.OrderRepository;
import com.example.dao.ProductRepository;
import com.example.dao.UserRepository;
import com.example.dto.CartItem;
import com.example.dto.OrderItems;
import com.example.pojo.entity.Order;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.User;

@Service
public class OrderService {

	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public String createOrder(String custId, List<CartItem> cartItems) {

    	String orderNum = "ORD" + System.currentTimeMillis();
    	
    	Optional<User> user = userRepository.findByLoginId(custId);

        for (CartItem item : cartItems) {
        Product product = productRepository.findById(Integer.parseInt(item.getProdId()))
                .orElseThrow(() -> new RuntimeException("查無商品"));

            Order orderRow = new Order();
            orderRow.setOrdNum(orderNum);
            orderRow.setOrdQty(Integer.parseInt(item.getQuantity()));
            orderRow.setAmount(product.getProdPrice() * 
            		Integer.parseInt(item.getQuantity()));
            orderRow.setProduct(product);
            orderRow.setUser(user.get());
            orderRow.setState("未付款");

            orderRepository.save(orderRow); // 多筆商品各自存一筆，綁定同一個 orderNum
        }
        return orderNum;
    }

    @Transactional
    public List<Order> queryOrder(String loginId){
    	User user = userRepository.findByLoginId(loginId)
    		    .orElseThrow(() -> new RuntimeException("查無此用戶"));

		List<Order> result = orderRepository.findByUser_LoginId(user.getLoginId());
		
		return result;
    }
    
    @Transactional
    public Page<Order> queryOrder(String loginId,Pageable page){
    	User user = userRepository.findByLoginId(loginId)
    		    .orElseThrow(() -> new RuntimeException("查無此用戶"));

		Page<Order> result = orderRepository.findByUser_LoginId(user.getLoginId(),page);
		
		return result;
    }
    
    @Transactional
    public void updateOrderState(String ordNum,String state) {
    	orderRepository.updateOrderState(ordNum, state);
    }
    
    @Transactional
    public List<OrderItems> queryAllOrder(){
    	List<Order> orders = orderRepository.findAll();
    	
    	Map<String, List<Order>> grouped = orders.stream()
    	        .collect(Collectors.groupingBy(Order::getOrdNum)); // 依訂單編號分組
    	//Collectors.groupingBy(x->x.getOrdNum())
    	//Collectors工具類，實作Collector這個規格書，所以才說Collectors工具類，就是Java Collector API
    	//Stream API要用Java Collector API，就必須呼叫collect()

    	    List<OrderItems> result = new ArrayList<>();

    	    for (Map.Entry<String, List<Order>> entry : grouped.entrySet()) {
    	    	//Entry不能直接當成一個類的原因：因為Entry是Map內部的資料結構
    	        result.add(new OrderItems(entry.getKey(), entry.getValue()));
    	    }

    	    return result;
    }
    
    @Transactional
    public int updateOrderStateByOrdNum(String state, String ordNum) {
    	return orderRepository.updateOrderStateByOrdNum(state, ordNum);
    }
    
    @Transactional
    public List<Object> findStateAndOrderNum() {
    	return orderRepository.findStateAndOrderNum();
    }
}
