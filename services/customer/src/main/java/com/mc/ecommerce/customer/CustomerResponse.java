package com.mc.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CustomerResponse(

        String id,

        String fistname,

        String lastname,

        String email,

        Address address
) {
}
