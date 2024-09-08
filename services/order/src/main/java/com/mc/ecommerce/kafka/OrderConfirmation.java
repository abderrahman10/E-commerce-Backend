package com.mc.ecommerce.kafka;

import com.mc.ecommerce.customer.CustomerResponse;
import com.mc.ecommerce.order.PaymentMethod;
import com.mc.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}