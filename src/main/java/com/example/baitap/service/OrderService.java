package com.example.baitap.service;

import com.example.baitap.model.Order;
import com.example.baitap.model.OrderDetail;
import com.example.baitap.model.CartItem;
import com.example.baitap.repository.OrderRepository;
import com.example.baitap.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    public Order placeOrder(String name, String address, String phone, Collection<CartItem> cartItems) {
        Order order = new Order();
        order.setCustomerName(name);
        order.setAddress(address);
        order.setPhone(phone);
        order.setOrderDate(new Date());
        
        double total = 0;
        List<OrderDetail> details = new ArrayList<>();
        
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setBook(item.getBook());
            detail.setQuantity(item.getQuantity());
            detail.setUnitPrice(item.getBook().getPrice());
            details.add(detail);
            total += item.getTotalPrice();
        }
        
        order.setTotalAmount(total);
        order.setOrderDetails(details);
        
        return orderRepository.save(order);
    }
}
