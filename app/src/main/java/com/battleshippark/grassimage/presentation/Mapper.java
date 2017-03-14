package com.battleshippark.grassimage.presentation;

import com.annimon.stream.Stream;
import com.battleshippark.grassimage.domain.DomainResult;
import com.battleshippark.grassimage.domain.DomainResultItem;

import java.util.List;

/**
 */

class Mapper {
    SearchResult from(DomainResult domainResult) {
        return new SearchResult(domainResult.total, from(domainResult.items));
    }

    private List<SearchResultItem> from(List<DomainResultItem> items) {
        return Stream.of(items).map(this::from).toList();
    }

    private SearchResultItem from(DomainResultItem item) {
        return new SearchResultItem(item.title, item.thumbnail);
    }
}
