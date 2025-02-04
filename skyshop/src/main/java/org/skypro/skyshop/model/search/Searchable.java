package org.skypro.skyshop.model.search;

import org.jetbrains.annotations.NotNull;

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
}
