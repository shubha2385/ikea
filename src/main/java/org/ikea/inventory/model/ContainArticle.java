package org.ikea.inventory.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContainArticle {
    private String art_id;
    private int amount_of;
}
