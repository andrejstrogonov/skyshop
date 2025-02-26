package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;

public interface ProductInterface {
    @JsonIgnore
    @NotNull String getSearchableTerm();

    @JsonIgnore
    @NotNull String getSearchableContentKind();
}
