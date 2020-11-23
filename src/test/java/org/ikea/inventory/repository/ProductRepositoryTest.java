package org.ikea.inventory.repository;

import org.ikea.inventory.helper.TestUtil;
import org.ikea.inventory.model.Product;
import org.ikea.inventory.repository.entity.ProductEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.support.ConfigurableConversionService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNotNull;

public class ProductRepositoryTest {
    @Mock
    private ProductCrudRepository productCrudRepositoryMock;

    @Mock
    private ConfigurableConversionService inventoryConversionService;

    @InjectMocks
    private ProductRepository underTest;

    @InjectMocks
    private ProductRepository productRepositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAllSuccessfully() {
        List<ProductEntity> productEntities = TestUtil.createProductEntityList();

        when(productCrudRepositoryMock.findAll()).thenReturn(productEntities);
        List<Product> response = productRepositoryMock.findAll();

        verify(productCrudRepositoryMock, times(1)).findAll();
        assertNotNull(response);
    }
}
