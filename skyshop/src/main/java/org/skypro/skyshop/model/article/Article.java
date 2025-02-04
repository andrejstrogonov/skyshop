package org.skypro.skyshop.model.article;

import org.jetbrains.annotations.NotNull;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    @NotNull
    private final String title;
    @NotNull
    private final String content;

    public @NotNull UUID getId() {
        return id;
    }

    @NotNull
    private final UUID id;

    /**
     * Конструктор.
     *
     * @param title   заголовок статьи
     * @param content текст статьи
     */
    public Article(@NotNull String title, @NotNull String content, @NotNull UUID id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

    /**
     * Получение заголовка статьи.
     *
     * @return заголовок статьи
     */
    @NotNull
    public String getTitle() {
        return title;
    }

    /**
     * Получение текста статьи.
     *
     * @return текст статьи
     */
    @NotNull
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return title + "\n" + content;
    }

    @NotNull
    public String getSearchableName() {
        return this.getClass().getSimpleName() + "-" + SEARCHABLE_CONTENT_KIND + "-" + this.hashCode();
    }

    @Override
    public @NotNull String getSearchableTerm() {
        return toString();
    }

    public static final String SEARCHABLE_CONTENT_KIND = "ARTICLE";

    @Override
    public @NotNull String getSearchableContentKind() {
        return SEARCHABLE_CONTENT_KIND;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
