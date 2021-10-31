package br.com.cursoudemy.productapi.modules.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoudemy.productapi.modules.product.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
