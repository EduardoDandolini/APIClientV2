package com.teste.projetoAPI.service;

import com.teste.projetoAPI.entities.Product;
import com.teste.projetoAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById (Long id) {
        Optional<Product> obj = productRepository.findById(id);
        return obj.get();
    }

    public Product insert (Product product) {
        return productRepository.save(product);
    }

    public void delete (Long id) {
        productRepository.deleteById(id);
    }

    public Product update (Long id, Product product) {
        Product entity = productRepository.getReferenceById(id);
        updateData(entity, product);
        return productRepository.save(entity);
    }

    private void updateData(Product entity, Product product) {
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
    }
}
