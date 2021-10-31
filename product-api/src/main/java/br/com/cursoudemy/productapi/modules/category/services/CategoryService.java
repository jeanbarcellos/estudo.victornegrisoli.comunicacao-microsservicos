package br.com.cursoudemy.productapi.modules.category.services;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CategoryResponse> findAll() {
        return categoryRepository
            .findAll()
            .stream()
            .map(CategoryResponse::of)
            .collect(Collectors.toList());
    }

    public List<CategoryResponse> findByDescription(String description) {
        if (isEmpty(description)) {
            throw new ValidationException("The category description must be informed.");
        }

        return categoryRepository
                .findByDescriptionIgnoreCaseContaining(description)
                .stream()
                .map(CategoryResponse::of)
                // .map(category -> CategoryResponse.of(category))
                .collect(Collectors.toList());
    }

    public CategoryResponse findByIdResponse(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The category ID was not informed.");
        }

        return CategoryResponse.of(findById(id));
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no supplier for the given ID."));
    }

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
