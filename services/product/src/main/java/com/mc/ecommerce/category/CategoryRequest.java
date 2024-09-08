package com.mc.ecommerce.category;

import com.mc.ecommerce.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.List;

public record CategoryRequest (
         Integer id,
         String name,
         String description

) {
}
