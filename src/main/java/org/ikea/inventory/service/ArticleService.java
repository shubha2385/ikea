package org.ikea.inventory.service;

import lombok.extern.slf4j.Slf4j;
import org.ikea.inventory.model.Article;
import org.ikea.inventory.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> loadArticles(List<Article> articles) {
        List<Article> articleResultList = articles.stream()
                .map(article -> articleRepository.saveArticle(article))
                .collect(Collectors.toList());

        return articleResultList;
    }

}
