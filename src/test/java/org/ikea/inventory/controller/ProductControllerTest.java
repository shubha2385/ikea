package org.ikea.inventory.controller;

import org.ikea.inventory.helper.TestUtil;
import org.ikea.inventory.model.Product;
import org.ikea.inventory.model.ProductList;
import org.ikea.inventory.service.ProductService;
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

public class ProductControllerTest {
    @Mock
    private ProductService productServiceMock;

    @InjectMocks
    private ProductController productControllerMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldLoadProductsSuccessfully() {
        ProductList products = TestUtil.createProductList1();
        List<Product> productList = TestUtil.createProductList();
        when(productServiceMock.loadProducts(any())).thenReturn(productList);
        ResponseEntity<ProductList> responseEntity = productControllerMock.loadProducts(products);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void shouldGetAllProductsSuccessfully() {
        List<Product> productList = TestUtil.createProductList();
        when(productServiceMock.findAllProducts()).thenReturn(productList);

        ResponseEntity<ProductList> responseEntity = productControllerMock.listAllProducts();
        verify(productServiceMock, times(1)).findAllProducts();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void shouldRemoveProductSuccessfully() {
        doNothing().when(productServiceMock).deleteProductById(any());
        productControllerMock.deleteProductById("1");
    }
}
