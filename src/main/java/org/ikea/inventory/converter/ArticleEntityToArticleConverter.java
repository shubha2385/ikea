package org.ikea.inventory.converter;

import org.ikea.inventory.model.Article;
import org.ikea.inventory.repository.entity.ArticleEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArticleEntityToArticleConverter implements Converter<ArticleEntity, Article> {

    @Override
    public Article convert(ArticleEntity source) {
        return Article.builder()
                .art_id(Long.toString(source.getArtId()))
                .name(source.getName())
                .stock(source.getStock())
                .build();
    }
}
