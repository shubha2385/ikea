package org.ikea.inventory.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductList {
    @NotEmpty
    private List<@Valid Product> products;
}

