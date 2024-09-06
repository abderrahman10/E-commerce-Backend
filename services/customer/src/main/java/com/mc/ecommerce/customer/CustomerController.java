package com.mc.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private  final CustomerService customerService;

    @PostMapping("/creat-customer")
    public ResponseEntity<String>CreateCustumer(@RequestBody @Valid CustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.CreateCustomer(customerRequest));
    }

    @PutMapping("/update-customer")
    public ResponseEntity<Void>UpdateCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        customerService.UpdateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/all-customers")
    public  ResponseEntity<List<CustomerResponse>>AllCustomers(){
        return  ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/customer-exist/{customer-id}")
    public  ResponseEntity<Boolean>CustomerExists(@PathVariable("customer-id") String customerId){
        return  ResponseEntity.ok(customerService.CustomerExistsById(customerId));
    }


    @GetMapping("/findCustomerById/{customer-id}")
    public  ResponseEntity<CustomerResponse>findCustomerById(@PathVariable("customer-id") String customerId){
        return  ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @DeleteMapping("/delete-customer/{customer-id}")
    public  ResponseEntity<Void>DeleteCustomer(@PathVariable("customer-id") String customerId){
        customerService.DeleteCustomer(customerId);
        return  ResponseEntity.accepted().build();
    }
}
