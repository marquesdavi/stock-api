package com.api.stock.service;

import com.api.stock.model.Purchase;
import com.api.stock.repository.IProductRepository;
import com.api.stock.repository.IPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final IPurchaseRepository purchaseRepository;
    private final IProductRepository productRepository;

    public List<Purchase> getSalesReport(Date startDate, Date endDate) {
        return purchaseRepository.findPurchasesByPeriod(startDate, endDate);
    }

    public List<Map<String, Object>> getStockReport() {
        List<Object[]> results = productRepository.findTopSellingProducts();
        return results.stream().map(result -> {
            Map<String, Object> item = new HashMap<>();
            item.put("product", result[0]);
            item.put("totalSales", result[1]);
            return item;
        }).collect(Collectors.toList());
    }
}
