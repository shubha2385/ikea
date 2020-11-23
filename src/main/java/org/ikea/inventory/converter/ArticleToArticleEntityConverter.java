package org.ikea.inventory.converter;

import org.ikea.inventory.model.Article;
import org.ikea.inventory.repository.entity.ArticleEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArticleToArticleEntityConverter implements Converter<Article, ArticleEntity> {

    @Override
    public ArticleEntity convert(Article source) {
        return ArticleEntity.builder()
                .artId(Long.parseLong(source.getArt_id()))
                .name(source.getName())
                .stock(source.getStock())
                .build();
    }
}
