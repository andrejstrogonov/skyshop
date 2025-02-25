package org.skypro.skyshop;

import org.jetbrains.annotations.NotNull;
import org.skypro.skyshop.model.search.Searchable;

class SearchableImpl implements Searchable {
    private final String content;

    public SearchableImpl(String content) {
        this.content = content;
    }

    @Override
    public String getSearchableTerm() {
        return content;
    }

     @Override
     public @NotNull String getSearchableContentKind() {
         return "";
     }

     @Override
     public boolean matches(String query) {
         return false;
     }
 }
