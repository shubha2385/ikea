package org.ikea.inventory.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.ikea.inventory.model.*;
import org.ikea.inventory.repository.entity.ArticleEntity;
import org.ikea.inventory.repository.entity.ContainArticleEntity;
import org.ikea.inventory.repository.entity.ProductEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUtil {

    private TestUtil() {

    }

    private static ObjectMapper mapper = new ObjectMapper();

    public static Inventory createInventory() {
        Inventory inventory = new Inventory();
        List<Article> articleList = new ArrayList<>();
        Article article = Article.builder()
                .name("leg")
                .stock("5").build();
        articleList.add(article);
        inventory.setInventory(articleList);
        return inventory;
    }

    public static List<Article> createArticleList() {
        return Collections.singletonList(createArticle());
    }

    public static Article createArticle() {
        Article article = Article.builder()
                .name("leg")
                .stock("5")
                .build();
        return article;
    }

    public static ProductList createProductList1() {
        ProductList productL = new ProductList();
        List<Product> productList = new ArrayList<>();
        ContainArticle containArticle = ContainArticle.builder()
                .art_id("1")
                .amount_of(2)
                .build();
        Product product = Product.builder()
                .name("chair")
                .contain_articles(Collections.singletonList(containArticle))
                .build();
        productList.add(product);
        productL.setProducts(productList);

        return productL;
    }

    public static List<Product> createProductList() {
        return Collections.singletonList(createProduct());
    }

    public static Product createProduct() {
        ContainArticle containArticle = ContainArticle.builder()
                .art_id("1")
                .amount_of(2)
                .build();

        Product product = Product.builder()
                .name("chair")
                .contain_articles(Collections.singletonList(containArticle))
                .build();
        return product;
    }

    public static List<ArticleEntity> createArticleEntityList() {
        return Collections.singletonList(createArticleEntity());
    }

    public static ArticleEntity createArticleEntity() {
        return ArticleEntity.builder()
                .name("leg")
                .stock("5")
                .build();
    }

    public static ProductEntity createProductEntity() {
        ContainArticleEntity containArticleEntity = ContainArticleEntity.builder()
                .id(1)
                .artId("1")
                .amount(3)
                .build();
        return ProductEntity.builder()
                .name("chair")
                .containArticles(Collections.singletonList(containArticleEntity))
                .id(1)
                .build();
    }

    public static List<ProductEntity> createProductEntityList() {
        return Collections.singletonList(createProductEntity());
    }

    public static JsonNode createProductsJson() {
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("", "");
        return objectNode;
    }
}
