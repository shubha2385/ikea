package org.ikea.inventory.repository.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity {

    @Id
    @Column(name = "art_id")
    private long artId;

    @Column(name = "name")
    private String name;

    @Column(name = "stock")
    private String stock;

}
