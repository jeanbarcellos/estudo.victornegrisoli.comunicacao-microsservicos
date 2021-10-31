package br.com.cursoudemy.productapi.modules.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.modules.product.dtos.CategoryRequest;
import br.com.cursoudemy.productapi.modules.product.dtos.CategoryResponse;
import br.com.cursoudemy.productapi.modules.product.services.CategoryService;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest request) {
        return categoryService.save(request);
    }

}
