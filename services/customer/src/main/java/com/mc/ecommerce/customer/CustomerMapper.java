package com.mc.ecommerce.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customerRequest) {
        if(customerRequest== null){
            return  null;
        }
    return  Customer.builder()
            .id(customerRequest.id())
            .fistname(customerRequest.fistname())
            .lastname(customerRequest.lastname())
            .email(customerRequest.email())
            .address(customerRequest.address())
            .build();
    }

    public CustomerResponse fromCustomerToCustomerResponse(Customer customer) {
        if(customer== null){
            return  null;
        }
        return  CustomerResponse.builder()
                .id(customer.getId())
                .fistname(customer.getFistname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
    }

