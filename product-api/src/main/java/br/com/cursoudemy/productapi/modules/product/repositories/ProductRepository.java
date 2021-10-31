package br.com.cursoudemy.productapi.modules.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoudemy.productapi.modules.product.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
