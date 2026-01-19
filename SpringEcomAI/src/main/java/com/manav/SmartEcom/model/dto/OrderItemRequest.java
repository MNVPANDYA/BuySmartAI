package com.manav.SmartEcom.model.dto;

public record OrderItemRequest(
        int productId,
        int quantity
) {}
