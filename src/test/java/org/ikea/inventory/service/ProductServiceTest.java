package org.ikea.inventory.service;

import org.ikea.inventory.exception.IllegalArgumentException;
import org.ikea.inventory.helper.TestConstant;
import org.ikea.inventory.helper.TestUtil;
import org.ikea.inventory.model.Article;
import org.ikea.inventory.model.Product;
import org.ikea.inventory.repository.ArticleRepository;
import org.ikea.inventory.repository.ProductRepository;
import org.ikea.inventory.repository.entity.ArticleEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class ProductServiceTest {
    @Mock
    private ArticleRepository articleRepositoryMock;

    @Mock
    private ProductRepository productRepositoryMock;

    @InjectMocks
    private ProductService productServiceMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testIfThrowIllegalArgumentExceptionWhenLoadProducts() {
        List<Product> productList = TestUtil.createProductList();
        List<ArticleEntity> articleEntityList = TestUtil.createArticleEntityList();
        Product product = TestUtil.createProduct();
        when(articleRepositoryMock.findAll()).thenReturn(articleEntityList);
        when(productRepositoryMock.saveProduct(any())).thenReturn(product);
        List<Product> response = productServiceMock.loadProducts(productList);

        verify(articleRepositoryMock, times(1)).findAll();
        assertEquals(TestConstant.PRODUCT_NAME, response.get(0).getName());
    }

    @Test
    public void testIfFindAllProductsSuccessfully() {
        List<Product> productList = TestUtil.createProductList();
        Article article = TestUtil.createArticle();

        when(productRepositoryMock.findAll()).thenReturn(productList);
        when(articleRepositoryMock.findArticleById(any())).thenReturn(article);
        List<Product> response = productServiceMock.findAllProducts();

        verify(productRepositoryMock, times(1)).findAll();
        verify(articleRepositoryMock, times(1)).findArticleById(any());
        assertEquals(TestConstant.PRODUCT_NAME, response.get(0).getName());
    }

    @Test
    public void testIfDeleteProductByIdSuccessfully() {
        Product product = TestUtil.createProduct();
        Article article = TestUtil.createArticle();
        when(productRepositoryMock.findProductById(any())).thenReturn(product);
        when(articleRepositoryMock.findArticleById(any())).thenReturn(article);

        doNothing().when(productRepositoryMock).deleteProductById(any());
        productServiceMock.deleteProductById("1");

        verify(productRepositoryMock, times(1)).findProductById(any());
        verify(articleRepositoryMock, times(1)).findArticleById(any());
    }


}
