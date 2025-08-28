package com.sarvika.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class OrderService {

    private final RestTemplate restTemplate;
    private final OrderRepository orderRepository;

    public OrderService(RestTemplate restTemplate, OrderRepository orderRepository) {
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        String url = "http://localhost:8081/users/" + order.getUserId();

        ResponseEntity<UserDTO> response =
                restTemplate.getForEntity(url, UserDTO.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            // user exists → save order
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("User not found!");
        }
    }
}
