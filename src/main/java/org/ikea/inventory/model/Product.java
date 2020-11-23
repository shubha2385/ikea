package org.ikea.inventory.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String name;
    private List<ContainArticle> contain_articles;
}
