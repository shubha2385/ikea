package org.ikea.inventory.converter;

import org.ikea.inventory.model.ContainArticle;
import org.ikea.inventory.model.Product;
import org.ikea.inventory.repository.entity.ContainArticleEntity;
import org.ikea.inventory.repository.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductEntityToProductConverter implements Converter<ProductEntity, Product> {

    @Override
    public Product convert(ProductEntity source) {
        return Product.builder()
                .name(source.getName())
                .contain_articles(convertContainArticles(source.getContainArticles()))
                .build();
    }

    private List<ContainArticle> convertContainArticles(List<ContainArticleEntity> containArticleEntityList) {
        List<ContainArticle> containArticleList = new ArrayList<>();
        for (ContainArticleEntity containArticleEntity : containArticleEntityList) {
            ContainArticle containArticle = ContainArticle.builder()
                    .art_id(String.valueOf(containArticleEntity.getArtId()))
                    .amount_of(containArticleEntity.getAmount())
                    .build();
            containArticleList.add(containArticle);
        }
        return containArticleList;
    }
}
