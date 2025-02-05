package org.skypro.skyshop.model.search;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SearchResult {
    private final UUID id;
    private final String name;
    private final String contentType;

    public SearchResult(@NotNull String id, String name, String contentType) {
        this.id = UUID.fromString(id);
        this.name = name;
        this.contentType = contentType;
    }
    public static Object fromSearchable(Searchable searchable) {
        return new SearchResult(searchable.getId(), searchable.getSearchableName(), searchable.getSearchableContentKind());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }
}
