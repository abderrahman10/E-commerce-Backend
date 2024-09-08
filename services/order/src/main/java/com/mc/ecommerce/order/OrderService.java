package com.mc.ecommerce.order;

import com.mc.ecommerce.customer.CustomerClient;
import com.mc.ecommerce.exception.BusinessException;
import com.mc.ecommerce.kafka.OrderConfirmation;
import com.mc.ecommerce.kafka.OrderProducer;
import com.mc.ecommerce.orderline.OrderLineRequest;
import com.mc.ecommerce.orderline.OrderLineService;
import com.mc.ecommerce.product.ProductClient;
import com.mc.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private  final OrderRepository orderRepository;
    private  final  OrderMapper orderMapper;
   private  final CustomerClient customerClient;
   private  final ProductClient productClient;
   private  final OrderLineService orderLineService;
   private  final OrderProducer orderProducer;

    public Integer createOrder(OrderRequest orderRequest) {
        //check the customer --> openFeign
  var customer=customerClient.findCustomerById(orderRequest.customerId())
          .orElseThrow(() -> new BusinessException(" can not create order :: Customer not found with the provided ID"));

        //purchase the products --> product microservice //here we will use RestTemplate
   var purchasedProducts= this.productClient.purchaseProducts(orderRequest.products());

        //persist order
  var order=this.orderRepository.save(this.orderMapper.toOrder(orderRequest));

        // persist order lines
        //Donc, pour chaque produit dans orderRequest.products() :
        //Une nouvelle lorderLine est créée avec les informations de ce produit (ID, quantité).
  for(PurchaseRequest purchaseRequest:orderRequest.products()){
  orderLineService.saveOrderLine(
          new OrderLineRequest(
                  null,
                  order.getId(),
                  purchaseRequest.productId(),
                  purchaseRequest.quantity()
          )
  );
  }
        // start payment process

        // send the order confirmation ---> notification microservice
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
   return  order.getId();
    }



    public List<OrderResponse> findAllOrders() {
        return  orderRepository.findAll().stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return  orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No order found with the ID: %d",orderId)));

    }
}
