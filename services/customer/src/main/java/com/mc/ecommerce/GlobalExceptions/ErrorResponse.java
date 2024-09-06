package com.mc.ecommerce.GlobalExceptions;

import java.util.Map;

public record ErrorResponse(
        Map<String,String>errors
) {
}
