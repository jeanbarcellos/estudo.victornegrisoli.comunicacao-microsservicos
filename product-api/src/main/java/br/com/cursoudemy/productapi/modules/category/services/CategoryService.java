package br.com.cursoudemy.productapi.modules.category.services;

import static org.springframework.util.ObjectUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.config.exception.ValidationException;
import br.com.cursoudemy.productapi.modules.category.dtos.CategoryRequest;
import br.com.cursoudemy.productapi.modules.category.dtos.CategoryResponse;
import br.com.cursoudemy.productapi.modules.category.models.Category;
import br.com.cursoudemy.productapi.modules.category.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse save(CategoryRequest request) {
        validateCategoryNameInformed(request);

        var category = categoryRepository.save(Category.of(request));

        return CategoryResponse.of(category);
    }

    private void validateCategoryNameInformed(CategoryRequest request) {
        if (isEmpty(request.getDescription())) {
            throw new ValidationException("The category description was not informed.");
        }
    }
}
