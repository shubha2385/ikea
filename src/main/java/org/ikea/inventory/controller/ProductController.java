package org.ikea.inventory.controller;

import lombok.extern.slf4j.Slf4j;
import org.ikea.inventory.helper.Constants;
import org.ikea.inventory.model.Product;
import org.ikea.inventory.model.ProductList;
import org.ikea.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = Constants.INVENTORY_SERVICE_BASE_URL)

public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/loadProducts", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductList> loadProducts(@Valid @RequestBody ProductList products) {
        List<Product> productResultList = productService.loadProducts(products.getProducts());
        final ProductList productListFinal = ProductList.builder().
                products(productResultList).build();
        log.debug("Products successfully loaded");
        return ResponseEntity.status(HttpStatus.CREATED).body(productListFinal);
    }

    @GetMapping(value = "/products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductList> listAllProducts() {
        List<Product> productList = productService.findAllProducts();
        final ProductList listOfProducts = ProductList.builder().products(productList).build();
        log.debug("Listed all products which are available in stock");
        return ResponseEntity.status(HttpStatus.OK).body(listOfProducts);
    }


    @DeleteMapping(value = "/product/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@Valid @NotEmpty @PathVariable final String productId) {
        log.debug("Delete product by id {}", productId);
        productService.deleteProductById(productId);
        log.debug("Successfully deleted product id {}", productId);

    }
}
