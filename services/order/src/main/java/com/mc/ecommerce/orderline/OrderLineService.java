package com.mc.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepositoryrepository;
    private final OrderLineMapper orderLineMappermapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = orderLineMappermapper.toOrderLine(request);
        return orderLineRepositoryrepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepositoryrepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMappermapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}