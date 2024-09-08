package com.mc.ecommerce.category;

import com.mc.ecommerce.product.Product;
import com.mc.ecommerce.product.ProductRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public Category toCategory(CategoryRequest request) {
        return Category.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .build();

    }
}
