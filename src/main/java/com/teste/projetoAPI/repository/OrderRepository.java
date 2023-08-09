package com.teste.projetoAPI.repository;

import com.teste.projetoAPI.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
