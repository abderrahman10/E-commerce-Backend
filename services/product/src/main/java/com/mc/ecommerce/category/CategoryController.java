package com.mc.ecommerce.category;

import com.mc.ecommerce.product.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private  final CategoryService categoryService;

    @PostMapping("create-category")
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid CategoryRequest categoryRequest){
        return  ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }




}
