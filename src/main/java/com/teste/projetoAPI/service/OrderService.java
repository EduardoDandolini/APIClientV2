package com.teste.projetoAPI.service;

import com.teste.projetoAPI.entities.Order;
import com.teste.projetoAPI.entities.User;
import com.teste.projetoAPI.repository.OrderRepository;
import com.teste.projetoAPI.service.exceptions.DataBaseException;
import com.teste.projetoAPI.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById (Long id) {
        Optional<Order> obj = orderRepository.findById(id);
        return obj.get();
    }

    public Order insert (Order order) {
        return orderRepository.save(order);
    }

    public void delete (Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public Order update (Long id,Order order) {
        try {
            Order entity = orderRepository.getReferenceById(id);
            updateData(entity, order);
            return orderRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Order entity, Order order) {
        entity.setClient(order.getClient());
        entity.setOrderStatus(order.getOrderStatus());
    }

}
