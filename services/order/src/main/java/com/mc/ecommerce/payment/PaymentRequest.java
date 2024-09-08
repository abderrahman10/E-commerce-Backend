package com.mc.ecommerce.payment;

import com.mc.ecommerce.customer.CustomerResponse;
import com.mc.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}