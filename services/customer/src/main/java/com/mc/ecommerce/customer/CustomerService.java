package com.mc.ecommerce.customer;

import com.mc.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private  final  CustomerRepository customerRepository;
  private  final  CustomerMapper customerMapper;


    public String CreateCustomer(CustomerRequest customerRequest) {
       var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return  customer.getId();
    }

    public void UpdateCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id())
                .orElseThrow(()-> new CustomerNotFoundException(String.format("cannot update customer:: No customer found with the provided ID:: %s",customerRequest.id())));

        customerRepository.save(customer);


    }


    public List<CustomerResponse> findAllCustomers() {
        return  customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomerToCustomerResponse)
                .collect(Collectors.toList());
    }

    public Boolean CustomerExistsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findCustomerById(String customerId) {
        return  customerRepository.findById(customerId)
                .map(customerMapper::fromCustomerToCustomerResponse)
                .orElseThrow(()-> new CustomerNotFoundException(String.format("cannot update customer:: No customer found with the provided ID:: %s",customerId)));
    }

    public void DeleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
