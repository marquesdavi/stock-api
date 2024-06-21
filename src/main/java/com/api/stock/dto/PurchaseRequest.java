package com.api.stock.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PurchaseRequest {
    private Long customer;
    private Long product;
    private Integer quantity;
}
