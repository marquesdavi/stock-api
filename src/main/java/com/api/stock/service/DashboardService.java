package com.api.stock.service;

import com.api.stock.repository.IProductRepository;
import com.api.stock.repository.IPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final IProductRepository productRepository;
    private final IPurchaseRepository purchaseRepository;

    public Map<String, Long> getTotalItemsInStock() {
        Long totalItemsInStock = productRepository.getTotalItemsInStock();
        Map<String, Long> response = new HashMap<>();
        response.put("totalItemsInStock", totalItemsInStock);
        return response;
    }

    public Map<String, Long> getTotalSalesByPeriod(Date startDate, Date endDate) {
        Long totalSales = purchaseRepository.getTotalSalesByPeriod(startDate, endDate);
        Map<String, Long> response = new HashMap<>();
        response.put("totalSales", totalSales);
        return response;
    }
}
