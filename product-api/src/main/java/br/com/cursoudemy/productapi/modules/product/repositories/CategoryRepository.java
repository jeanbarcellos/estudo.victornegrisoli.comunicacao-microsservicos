package br.com.cursoudemy.productapi.modules.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoudemy.productapi.modules.product.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}