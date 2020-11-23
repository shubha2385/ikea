package org.ikea.inventory.controller;

import org.ikea.inventory.helper.TestUtil;
import org.ikea.inventory.model.Article;
import org.ikea.inventory.model.Inventory;
import org.ikea.inventory.service.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ArticleControllerTest {

    @Mock
    private ArticleService articleServiceMock;

    @InjectMocks
    private ArticleController articleControllerMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldLoadArtifactsSuccessfully() {
        Inventory inventory = TestUtil.createInventory();
        List<Article> articleList = TestUtil.createArticleList();

        when(articleServiceMock.loadArticles(any())).thenReturn(articleList);
        ResponseEntity<Inventory> responseEntity = articleControllerMock.loadArticles(inventory);
        verify(articleServiceMock, times(1)).loadArticles(any());

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
