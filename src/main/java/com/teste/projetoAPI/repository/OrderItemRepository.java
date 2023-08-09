package com.teste.projetoAPI.repository;

import com.teste.projetoAPI.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
