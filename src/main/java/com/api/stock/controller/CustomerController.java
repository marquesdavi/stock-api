package com.api.stock.controller;

import com.api.stock.dto.CustomerRequest;
import com.api.stock.model.Customer;
import com.api.stock.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void create(@RequestBody CustomerRequest customerRequest) {
        service.create(customerRequest);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        service.update(id, customerRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
