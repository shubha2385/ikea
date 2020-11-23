package org.ikea.inventory.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "contain_article")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContainArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private ProductEntity productContainArt;

    @Column(name = "art_id")
    private String artId;

    @Column(name = "amount_of")
    private int amount;
}
