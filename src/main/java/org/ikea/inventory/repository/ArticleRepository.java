package org.ikea.inventory.repository;

import lombok.extern.slf4j.Slf4j;
import org.ikea.inventory.model.Article;
import org.ikea.inventory.repository.entity.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Component
public class ArticleRepository {

    @Autowired
    private ArticleCrudRepository articleCrudRepository;

    @Autowired
    private ConfigurableConversionService inventoryConversionService;

    public List<ArticleEntity> findAll() {
        return articleCrudRepository.findAll();
    }

    public Article findArticleById(String articleId) {
        ArticleEntity articleEntity = articleCrudRepository.findById(Long.parseLong(articleId));
        return inventoryConversionService.convert(articleEntity, Article.class);
    }

    @Transactional
    public Article updateArticleStock(String articleId, String articleStock, int stock) {
        ArticleEntity articleEntity = articleCrudRepository.findById(Long.parseLong(articleId));
        int newStock = 0;
        if (Integer.parseInt(articleStock) > stock) {
            newStock = Integer.parseInt(articleStock) - stock;
        }
        articleEntity.setStock(String.valueOf(newStock));

        ArticleEntity artEntity = articleCrudRepository.save(articleEntity);
        return inventoryConversionService.convert(artEntity, Article.class);
    }


    @Transactional
    public Article saveArticle(Article article) {
        ArticleEntity artEntity = articleCrudRepository.save(
                inventoryConversionService.convert(article, ArticleEntity.class));
        return inventoryConversionService.convert(artEntity, Article.class);
    }

}
