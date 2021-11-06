package br.com.cursoudemy.productapi.modules.product.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cursoudemy.productapi.modules.product.dtos.ProductStockDTO;
import br.com.cursoudemy.productapi.modules.product.services.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductStockListener {

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void recieveProductStockMessage(ProductStockDTO product) throws JsonProcessingException {

        log.info(
            "Recieving message with data: {} and TransactionID: {}",
            new ObjectMapper().writeValueAsString(product),
            product.getTransactionid()
        );

        productService.updateProductStock(product);
    }

}
