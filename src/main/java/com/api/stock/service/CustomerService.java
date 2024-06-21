package com.api.stock.service;

import com.api.stock.dto.CustomerRequest;
import com.api.stock.model.Customer;
import com.api.stock.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ICustomerRepository repository;

    public void create(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequest, customer);
        repository.save(customer);
    }

    public Customer getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found!"));
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public void update(Long id, CustomerRequest customerRequest) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found!"));
        customer.setName(customerRequest.name());
        repository.save(customer);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Customer not found!");
        }
        repository.deleteById(id);
    }
}
