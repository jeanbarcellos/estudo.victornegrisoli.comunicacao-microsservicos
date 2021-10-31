package br.com.cursoudemy.productapi.modules.product.services;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.config.exception.ValidationException;
import br.com.cursoudemy.productapi.modules.category.services.CategoryService;
import br.com.cursoudemy.productapi.modules.product.dtos.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.dtos.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.models.Product;
import br.com.cursoudemy.productapi.modules.product.repositories.ProductRepository;
import br.com.cursoudemy.productapi.modules.supplier.services.SupplierService;

@Service
public class ProductService {

    private static final Integer ZERO = 0;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;

    public List<ProductResponse> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findByName(String name) {
        if (isEmpty(name)) {
            throw new ValidationException("The product name must be informed.");
        }
        return productRepository
            .findByNameIgnoreCaseContaining(name)
            .stream()
            .map(ProductResponse::of)
            .collect(Collectors.toList());
    }

    public List<ProductResponse> findBySupplierId(Integer supplierId) {
        if (isEmpty(supplierId)) {
            throw new ValidationException("The product' supplier ID name must be informed.");
        }
        return productRepository
            .findBySupplierId(supplierId)
            .stream()
            .map(ProductResponse::of)
            .collect(Collectors.toList());
    }

    public List<ProductResponse> findByCategoryId(Integer categoryId) {
        if (isEmpty(categoryId)) {
            throw new ValidationException("The product' category ID name must be informed.");
        }
        return productRepository
            .findByCategoryId(categoryId)
            .stream()
            .map(ProductResponse::of)
            .collect(Collectors.toList());
    }

    public ProductResponse findByIdResponse(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The product ID was not informed.");
        }

        return ProductResponse.of(findById(id));
    }

    public Product findById(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The product ID was not informed.");
        }

        return productRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no product for the given ID."));
    }

    public ProductResponse save(ProductRequest request) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierIdInformed(request);

        var category = categoryService.findById(request.getCategoryId());

        var supplier = supplierService.findById(request.getSupplierId());

        var product = Product.of(request, supplier, category);

        productRepository.save(product);

        return ProductResponse.of(product);
    }

    private void validateProductDataInformed(ProductRequest request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("The product's name was not informed.");
        }
        if (isEmpty(request.getQuantityAvailable())) {
            throw new ValidationException("The product's quantity was not informed.");
        }
        if (request.getQuantityAvailable() <= ZERO) {
            throw new ValidationException("The quantity should not be less or equal to zero.");
        }
    }

    private void validateCategoryAndSupplierIdInformed(ProductRequest request) {
        if (isEmpty(request.getCategoryId())) {
            throw new ValidationException("The category ID was not informed.");
        }
        if (isEmpty(request.getSupplierId())) {
            throw new ValidationException("The supplier ID was not informed.");
        }
    }
}