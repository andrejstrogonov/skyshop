package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    @Autowired
    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }
    public Collection<SearchResult> search(String query) {
        Collection<Searchable> searchables = storageService.getAllSearchables();

        return searchables.stream()
                .filter(searchable -> searchable.matches(query))
                .map(searchable -> new SearchResult(searchable.getId(), searchable.getSearchableContentKind(), searchable.getSearchableTerm()))
                .collect(Collectors.toList());
    }
}


