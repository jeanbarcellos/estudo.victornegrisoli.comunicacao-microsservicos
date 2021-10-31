package br.com.cursoudemy.productapi.modules.supplier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.modules.supplier.dtos.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.dtos.SupplierResponse;
import br.com.cursoudemy.productapi.modules.supplier.services.SupplierService;

@RestController
@RequestMapping("api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public SupplierResponse save(@RequestBody SupplierRequest request) {
        return supplierService.save(request);
    }
}
