package org.ikea.inventory.repository;

import org.ikea.inventory.repository.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCrudRepository extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findAll();

    ProductEntity findById(long id);

    void delete(ProductEntity productEntity);

    void deleteById(long id);

    ProductEntity save(ProductEntity productEntity);

}
