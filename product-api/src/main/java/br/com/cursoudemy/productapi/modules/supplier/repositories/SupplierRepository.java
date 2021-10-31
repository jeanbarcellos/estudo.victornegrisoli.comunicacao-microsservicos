package br.com.cursoudemy.productapi.modules.supplier.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoudemy.productapi.modules.supplier.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
