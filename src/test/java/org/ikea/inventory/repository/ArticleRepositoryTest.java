package org.ikea.inventory.repository;

import org.ikea.inventory.helper.TestConstant;
import org.ikea.inventory.helper.TestUtil;
import org.ikea.inventory.model.Article;
import org.ikea.inventory.repository.entity.ArticleEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.support.ConfigurableConversionService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class ArticleRepositoryTest {

    @Mock
    private ArticleCrudRepository articleCrudRepositoryMock;

    @Mock
    private ConfigurableConversionService inventoryConversionService;

    @InjectMocks
    private ArticleRepository underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAllSuccessfully() {
        List<ArticleEntity> articleEntities = TestUtil.createArticleEntityList();
        when(articleCrudRepositoryMock.findAll()).thenReturn(articleEntities);

        List<ArticleEntity> response = underTest.findAll();

        verify(articleCrudRepositoryMock, times(1)).findAll();
        assertEquals(TestConstant.ARTICLE_NAME, response.get(0).getName());
    }

    @Test
    public void shouldSaveArtifactSuccessfully() {
        Article article = TestUtil.createArticle();
        ArticleEntity articleEntity = TestUtil.createArticleEntity();
        when(articleCrudRepositoryMock.save(any(ArticleEntity.class))).thenReturn(articleEntity);
        underTest.saveArticle(article);
        verify(articleCrudRepositoryMock, times(1)).save(any());
    }

}
