package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerRepository;
import com.example.dao.OrderRepository;
import com.example.dao.ProductRepository;
import com.example.dto.CartItem;
import com.example.dto.OrderItems;
import com.example.pojo.entity.Customer;
import com.example.pojo.entity.Order;
import com.example.pojo.entity.Product;

@Service
public class OrderService {

	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public String createOrder(String custId, List<CartItem> cartItems) {

    	String orderNum = "ORD" + System.currentTimeMillis();
    	
    	Optional<Customer> customer = customerRepository.findByLoginId(custId);

        for (CartItem item : cartItems) {
        Product product = productRepository.findById(Integer.parseInt(item.getProdId()))
                .orElseThrow(() -> new RuntimeException("查無商品"));

            Order orderRow = new Order();
            orderRow.setOrdNum(orderNum);
            orderRow.setOrdQty(Integer.parseInt(item.getQuantity()));
            orderRow.setAmount(product.getProdPrice() * 
            		Integer.parseInt(item.getQuantity()));
            orderRow.setProduct(product);
            orderRow.setCustomer(customer.get());
            orderRow.setState("未付款");

            orderRepository.save(orderRow); // 多筆商品各自存一筆，綁定同一個 orderNum
        }
        return orderNum;
    }
    
    public List<Order> queryOrder(String loginId){
    	Customer customer = customerRepository.findByLoginId(loginId)
    		    .orElseThrow(() -> new RuntimeException("查無此用戶"));

		List<Order> result = orderRepository.findByCustomer_CustId(customer.getCustId());
		
		return result;
    }
    
    public void updateOrderState(String ordNum,String state) {
    	orderRepository.updateOrderState(ordNum, state);
    }
    
    public List<OrderItems> queryAllOrder(){
    	List<Order> orders = orderRepository.findAll();
    	
    	Map<String, List<Order>> grouped = orders.stream()
    	        .collect(Collectors.groupingBy(Order::getOrdNum)); // 依訂單編號分組

    	    List<OrderItems> result = new ArrayList<>();

    	    for (Map.Entry<String, List<Order>> entry : grouped.entrySet()) {
    	        result.add(new OrderItems(entry.getKey(), entry.getValue()));
    	    }

    	    return result;
    }
    
    public int updateOrderStateByOrdNum(String state, String ordNum) {
    	return orderRepository.updateOrderStateByOrdNum(state, ordNum);
    }
}
