package org.ikea.inventory.service;

import org.ikea.inventory.helper.TestConstant;
import org.ikea.inventory.helper.TestUtil;
import org.ikea.inventory.model.Article;
import org.ikea.inventory.repository.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepositoryMock;

    @InjectMocks
    private ArticleService articleServiceMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIfLoadArticlesSuccessfully() {
        List<Article> articleList = TestUtil.createArticleList();
        Article article = TestUtil.createArticle();
        when(articleRepositoryMock.saveArticle(any())).thenReturn(article);
        List<Article> response = articleServiceMock.loadArticles(articleList);

        verify(articleRepositoryMock, times(1)).saveArticle(any());
        Assert.assertEquals(TestConstant.ARTICLE_NAME, response.get(0).getName());
    }
}


