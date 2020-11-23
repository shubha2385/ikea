package org.ikea.inventory.repository;

import org.ikea.inventory.repository.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCrudRepository extends CrudRepository<ArticleEntity, Long> {

    List<ArticleEntity> findAll();

    ArticleEntity save(ArticleEntity articleEntity);

    ArticleEntity findById(long id);
}
