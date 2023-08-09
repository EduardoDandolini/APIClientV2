package com.teste.projetoAPI.repository;

import com.teste.projetoAPI.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
