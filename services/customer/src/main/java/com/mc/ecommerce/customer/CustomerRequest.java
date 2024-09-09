package com.mc.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record CustomerRequest(
                 String id,
                 @NotNull(message = "Customer firstname is required")
                 String fistname,

                 @NotNull(message = "Customer lastname is required")
                 String lastname,

                 @NotNull(message = "Customer email is required")
                 @Email(message = "Customer email is not  valid ")
                 String email,

                 Address address
) {
}
