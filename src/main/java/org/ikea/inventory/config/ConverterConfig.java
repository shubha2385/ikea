package org.ikea.inventory.config;

import org.ikea.inventory.converter.ArticleEntityToArticleConverter;
import org.ikea.inventory.converter.ArticleToArticleEntityConverter;
import org.ikea.inventory.converter.ProductEntityToProductConverter;
import org.ikea.inventory.converter.ProductToProductEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.GenericConversionService;

@Configuration
public class ConverterConfig {

    @Autowired
    private ArticleToArticleEntityConverter articleToArticleEntityConverterConfigurationConverter;

    @Autowired
    private ArticleEntityToArticleConverter articleEntityToArticleConverterConfigurationConverter;

    @Autowired
    private ProductToProductEntityConverter productToProductEntityConverterConfigurationConverter;

    @Autowired
    private ProductEntityToProductConverter productEntityToProductConverterConfigurationConverter;


    @Bean
    public ConfigurableConversionService inventoryConversionService() {
        final ConfigurableConversionService conversionService = new GenericConversionService();
        conversionService.addConverter(articleToArticleEntityConverterConfigurationConverter);
        conversionService.addConverter(articleEntityToArticleConverterConfigurationConverter);
        conversionService.addConverter(productToProductEntityConverterConfigurationConverter);
        conversionService.addConverter(productEntityToProductConverterConfigurationConverter);
        return conversionService;
    }
}
