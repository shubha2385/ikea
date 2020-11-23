package org.ikea.inventory.converter;

import lombok.extern.slf4j.Slf4j;
import org.ikea.inventory.model.ContainArticle;
import org.ikea.inventory.model.Product;
import org.ikea.inventory.repository.entity.ContainArticleEntity;
import org.ikea.inventory.repository.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ProductToProductEntityConverter implements Converter<Product, ProductEntity> {

    @Override
    public ProductEntity convert(Product source) {

        ProductEntity productEntity = ProductEntity.builder()
                .name(source.getName())
                .containArticles(new ArrayList<>())
                .build();

        convertContainArticles(productEntity, source.getContain_articles());
        return productEntity;

    }

    private void convertContainArticles(final ProductEntity productEntity,
                                        List<ContainArticle> containArticleList) {
        if (containArticleList != null) {
            for (ContainArticle containArticle : containArticleList) {
                ContainArticleEntity containArticleEntity;
                containArticleEntity = ContainArticleEntity.builder()
                        .artId(containArticle.getArt_id())
                        .amount(containArticle.getAmount_of())
                        .productContainArt(productEntity)
                        .build();
                productEntity.getContainArticles().add(containArticleEntity);
            }
        }
    }
}