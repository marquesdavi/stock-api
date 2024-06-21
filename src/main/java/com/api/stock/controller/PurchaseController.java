package com.api.stock.controller;

import com.api.stock.dto.PurchaseRequest;
import com.api.stock.model.Purchase;
import com.api.stock.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {
    private final PurchaseService service;

    @GetMapping("/{id}")
    public Purchase getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Purchase> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void create(@RequestBody PurchaseRequest purchaseRequest) {
        service.create(purchaseRequest);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody PurchaseRequest purchaseRequest) {
        service.update(id, purchaseRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
