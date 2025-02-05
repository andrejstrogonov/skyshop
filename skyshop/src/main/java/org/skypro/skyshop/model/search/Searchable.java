package org.skypro.skyshop.model.search;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface Searchable {
    /**
     * @return Имя объекта Searchable
     */
    @SuppressWarnings("unused")
    @NotNull
    default String getSearchableName() {
        return this.getClass().getSimpleName() + "-" + this.hashCode();
    }

    /**
     * @return Содержимое, по которому производится поиск
     */
    @NotNull
    String getSearchableTerm();

    /**
     * @return Вид содержимого, по которому производится поиск
     */
    @SuppressWarnings("unused")
    @NotNull
    String getSearchableContentKind();

    @NotNull
    default String getId() {return UUID.randomUUID().toString();}

    boolean matches(String query);
}
