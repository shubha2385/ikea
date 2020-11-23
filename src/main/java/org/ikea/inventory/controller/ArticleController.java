package org.ikea.inventory.controller;

import lombok.extern.slf4j.Slf4j;
import org.ikea.inventory.helper.Constants;
import org.ikea.inventory.model.Article;
import org.ikea.inventory.model.Inventory;
import org.ikea.inventory.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = Constants.INVENTORY_SERVICE_BASE_URL)
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/loadArticles", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Inventory> loadArticles(@Valid @RequestBody Inventory inventory) {
        List<Article> articleResultList = articleService.loadArticles(inventory.getInventory());
        final Inventory inventoryResponse = Inventory.builder()
                .inventory(articleResultList).build();
        log.debug("Articles successfully loaded");
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryResponse);
    }
}
