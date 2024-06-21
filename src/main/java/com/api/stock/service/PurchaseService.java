package com.api.stock.service;

import com.api.stock.dto.PurchaseRequest;
import com.api.stock.model.Customer;
import com.api.stock.model.Product;
import com.api.stock.model.Purchase;
import com.api.stock.repository.ICustomerRepository;
import com.api.stock.repository.IProductRepository;
import com.api.stock.repository.IPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final IPurchaseRepository repository;
    private final IProductRepository productRepository;
    private final ICustomerRepository customerRepository;

    public void create(PurchaseRequest purchaseRequest) {
        Purchase purchase = new Purchase();

        Product product = productRepository.findById(purchaseRequest.getProduct())
                .orElseThrow(() -> new NoSuchElementException("Product not found!"));

        Customer customer = customerRepository.findById(purchaseRequest.getCustomer())
                .orElseThrow(() -> new NoSuchElementException("Customer not found!"));

        if (product.getAmount() < purchaseRequest.getQuantity()) {
            throw new IllegalArgumentException("Insufficient product quantity available!");
        }

        Double totalValue = purchaseRequest.getQuantity() * product.getValue();
        purchase.setTotalValue(totalValue);
        purchase.setCustomer(customer);
        purchase.setProduct(product);
        purchase.setQuantity(purchaseRequest.getQuantity());

        product.setAmount(product.getAmount() - purchaseRequest.getQuantity());
        productRepository.save(product);
        repository.save(purchase);
    }

    public Purchase getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Purchase not found!"));
    }

    public List<Purchase> getAll() {
        return repository.findAll();
    }

    public void update(Long id, PurchaseRequest purchaseRequest) {
        Purchase purchase = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Purchase not found!"));

        Product oldProduct = purchase.getProduct();
        oldProduct.setAmount(oldProduct.getAmount() + purchase.getQuantity());
        productRepository.save(oldProduct);

        if (purchaseRequest.getProduct() != null) {
            Product newProduct = productRepository.findById(purchaseRequest.getProduct())
                    .orElseThrow(() -> new NoSuchElementException("Product not found!"));

            if (newProduct.getAmount() < purchaseRequest.getQuantity()) {
                throw new IllegalArgumentException("Insufficient product quantity available!");
            }

            newProduct.setAmount(newProduct.getAmount() - purchaseRequest.getQuantity());
            purchase.setProduct(newProduct);
            productRepository.save(newProduct);
        }

        if (purchaseRequest.getCustomer() != null) {
            Customer customer = customerRepository.findById(purchaseRequest.getCustomer())
                    .orElseThrow(() -> new NoSuchElementException("Customer not found!"));
            purchase.setCustomer(customer);
        }

        if (purchaseRequest.getQuantity() != null) {
            if (purchase.getProduct().getAmount() < purchaseRequest.getQuantity()) {
                throw new IllegalArgumentException("Insufficient product quantity available!");
            }
            purchase.setQuantity(purchaseRequest.getQuantity());
        }

        Double totalValue = purchase.getQuantity() * purchase.getProduct().getValue();
        purchase.setTotalValue(totalValue);

        repository.save(purchase);
    }

    public void delete(Long id) {
        Purchase purchase = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Purchase not found!"));

        Product product = purchase.getProduct();
        product.setAmount(product.getAmount() + purchase.getQuantity());
        productRepository.save(product);

        repository.deleteById(id);
    }
}
