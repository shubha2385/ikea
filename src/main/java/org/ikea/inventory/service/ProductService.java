package org.ikea.inventory.service;

import lombok.extern.slf4j.Slf4j;
import org.ikea.inventory.exception.IllegalArgumentException;
import org.ikea.inventory.helper.Constants;
import org.ikea.inventory.helper.ErrorCodes;
import org.ikea.inventory.model.Article;
import org.ikea.inventory.model.ContainArticle;
import org.ikea.inventory.model.Product;
import org.ikea.inventory.repository.ArticleRepository;
import org.ikea.inventory.repository.ProductRepository;
import org.ikea.inventory.repository.entity.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<Product> loadProducts(List<Product> products) {
        List<Product> productResultList = new ArrayList<>();
        List<Long> articleIdList = getArticleIds();
        for (Product product : products) {
            for (int i = 0; i < product.getContain_articles().size(); i++) {
                if (!articleIdList.contains(Long.parseLong(product.getContain_articles().get(i).getArt_id()))) {
                    throw new IllegalArgumentException(ErrorCodes.ARTICLE_ID_NOT_FOUND_CODE,
                            Constants.ARTICLE_ID_NOT_EXISTS);
                }
            }
            Product productRes = productRepository.saveProduct(product);
            productResultList.add(productRes);
        }

        return productResultList;
    }

    public List<Product> findAllProducts() {
        final List<Product> availableProductList = new ArrayList<>();

        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            final Product pro = new Product();
            pro.setName(product.getName());
            final List<ContainArticle> containArticleList = new ArrayList<>();
            for (int i = 0; i < product.getContain_articles().size(); i++) {
                Article article = findArticleById(product.getContain_articles().get(i).getArt_id());
                if (Integer.parseInt(article.getStock()) > product.getContain_articles().get(i).getAmount_of()) {
                    final ContainArticle ca = new ContainArticle();
                    ca.setArt_id(product.getContain_articles().get(i).getArt_id());
                    ca.setAmount_of(product.getContain_articles().get(i).getAmount_of());
                    containArticleList.add(ca);
                }
                pro.setContain_articles(containArticleList);
            }

            if (!containArticleList.isEmpty()) {
                availableProductList.add(pro);
            }
        }
        return availableProductList;
    }

    public void deleteProductById(String productId) {
        Product product = productRepository.findProductById(productId);
        for (ContainArticle containArticle : product.getContain_articles()) {
            Article article = articleRepository.findArticleById(containArticle.getArt_id());
            articleRepository.updateArticleStock(article.getArt_id(), article.getStock(), containArticle.getAmount_of());
        }
        productRepository.deleteProductById(productId);
    }

    public Article findArticleById(String articleId) {
        return articleRepository.findArticleById(articleId);
    }

    private List<Long> getArticleIds() {
        List<ArticleEntity> articleEntityList = articleRepository.findAll();
        List<Long> articleIdList = articleEntityList.stream()
                .map(ArticleEntity::getArtId)
                .collect(Collectors.toList());
        return articleIdList;
    }

}
