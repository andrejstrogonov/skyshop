package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @Captor
    private ArgumentCaptor<String> queryCaptor;

    private SearchService searchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        searchService = new SearchService(storageService);
    }

    @Test
    public void shouldReturnEmptyListWhenNoSearchablesAreFound() {
        // Arrange
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        // Act
        List<SearchResult> searchResults = new ArrayList<>(searchService.search("test"));

        // Assert
        assertEquals(Collections.emptyList(), searchResults);
    }
    @Test
    public void shouldCallStorageServiceWithExpectedQuery() {
        // Arrange
        List<Searchable> searchables = Arrays.asList(
                new SearchableImpl("Object 1"),
                new SearchableImpl("Object 2"),
                new SearchableImpl("Object 3")
        );
        when(storageService.getAllSearchables()).thenReturn(searchables);

        // Act
        searchService.search("Expected query");

        // Assert
        assertEquals("Expected query", queryCaptor.getValue());
    }
    @Test
    public void shouldReturnSearchableResults() {
        // Arrange
        List<Searchable> searchables = Arrays.asList(
                new SearchableImpl("Object 1"),
                new SearchableImpl("Object 2"),
                new SearchableImpl("Expected object")
        );
        when(storageService.getAllSearchables()).thenReturn(searchables);

        // Act
        List<SearchResult> searchResults = new ArrayList<>(searchService.search("Expected query"));

        // Assert
        assertEquals(1, searchResults.size());
        assertEquals("Expected object", searchResults.get(0).getName());
    }

}