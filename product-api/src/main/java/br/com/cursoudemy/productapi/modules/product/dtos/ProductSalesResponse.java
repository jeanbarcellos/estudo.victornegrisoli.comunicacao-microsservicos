package br.com.cursoudemy.productapi.modules.product.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.cursoudemy.productapi.modules.category.dtos.CategoryResponse;
import br.com.cursoudemy.productapi.modules.product.models.Product;
import br.com.cursoudemy.productapi.modules.supplier.dtos.SupplierResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSalesResponse {

    private Integer id;
    private String name;
    private Integer quantityAvailable;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    private SupplierResponse supplier;
    private CategoryResponse category;
    private List<String> sales;

    public static ProductSalesResponse of(Product product, List<String> sales) {
        return ProductSalesResponse
            .builder()
            .id(product.getId())
            .name(product.getName())
            .quantityAvailable(product.getQuantityAvailable())
            .createdAt(product.getCreatedAt())
            .supplier(SupplierResponse.of(product.getSupplier()))
            .category(CategoryResponse.of(product.getCategory()))
            .sales(sales)
            .build();
    }
}
