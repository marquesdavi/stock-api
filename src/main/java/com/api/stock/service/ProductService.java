package com.api.stock.service;

import com.api.stock.dto.ProductRequest;
import com.api.stock.model.Product;
import com.api.stock.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final IProductRepository repository;

    public void create(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        repository.save(product);
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found!"));
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public void update(Long id, ProductRequest productRequest) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found!"));
        BeanUtils.copyProperties(productRequest, product);
        product.setId(id);
        repository.save(product);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Product not found!");
        }
        repository.deleteById(id);
    }
}
