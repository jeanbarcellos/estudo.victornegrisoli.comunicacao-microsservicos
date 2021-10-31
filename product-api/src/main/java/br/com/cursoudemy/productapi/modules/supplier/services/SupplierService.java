package br.com.cursoudemy.productapi.modules.supplier.services;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.config.exception.ValidationException;
import br.com.cursoudemy.productapi.modules.supplier.dtos.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.dtos.SupplierResponse;
import br.com.cursoudemy.productapi.modules.supplier.models.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.repositories.SupplierRepository;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<SupplierResponse> findAll() {
        return supplierRepository
            .findAll()
            .stream()
            .map(SupplierResponse::of)
            .collect(Collectors.toList());
    }

    public List<SupplierResponse> findByName(String name) {
        if (isEmpty(name)) {
            throw new ValidationException("The supplier name must be informed.");
        }

        return supplierRepository
                .findByNameIgnoreCaseContaining(name)
                .stream()
                .map(SupplierResponse::of)
                // .map(supplier -> SupplierResponse.of(supplier))
                .collect(Collectors.toList());
    }

    public SupplierResponse findByIdResponse(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The supplier ID was not informed.");
        }

        return SupplierResponse.of(findById(id));
    }

    public Supplier findById(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The supplier ID was not informed.");
        }

        return supplierRepository
            .findById(id)
            .orElseThrow(() -> new ValidationException("There's no supplier for the given ID."));
    }

    public SupplierResponse save(SupplierRequest request) {
        validateSupplierNameInformed(request);

        var supplier = supplierRepository.save(Supplier.of(request));

        return SupplierResponse.of(supplier);
    }

    private void validateSupplierNameInformed(SupplierRequest request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("The supplier name was not informed.");
        }
    }
}
