package com.mc.ecommerce.category;

import com.mc.ecommerce.product.ProductMapper;
import com.mc.ecommerce.product.ProductRepository;
import com.mc.ecommerce.product.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public Integer createCategory(
            CategoryRequest request
    ) {
        var category = categoryMapper.toCategory(request);
        return categoryRepository.save(category).getId();
    }

}