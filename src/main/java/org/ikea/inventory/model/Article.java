package org.ikea.inventory.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Article {

    private String art_id;
    private String name;
    private String stock;
}