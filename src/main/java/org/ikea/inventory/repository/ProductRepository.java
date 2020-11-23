package org.ikea.inventory.repository;

import lombok.extern.slf4j.Slf4j;
import org.ikea.inventory.model.Product;
import org.ikea.inventory.repository.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class ProductRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Autowired
    private ConfigurableConversionService inventoryConversionService;

    public Product findProductById(String productId) {
        log.info("Finding product by id");
        ProductEntity productEntity = productCrudRepository.findById(Long.parseLong(productId));
        return inventoryConversionService.convert(productEntity, Product.class);
    }

    public void deleteProductById(String productId) {
        productCrudRepository.deleteById(Long.parseLong(productId));
    }

    public List<Product> findAll() {
        log.info("Finding all products");
        List<Product> productList = new ArrayList<>();
        List<ProductEntity> productEntityList = productCrudRepository.findAll();
        for (ProductEntity productEntity : productEntityList) {
            productList.add(inventoryConversionService.convert(productEntity, Product.class));
        }
        return productList;
    }

    @Transactional
    public Product saveProduct(Product product) {
        log.info("Saving product");
        ProductEntity productEntity = productCrudRepository.save(Objects.requireNonNull(
                inventoryConversionService.convert(product, ProductEntity.class)));
        return inventoryConversionService.convert(productEntity, Product.class);
    }


}
