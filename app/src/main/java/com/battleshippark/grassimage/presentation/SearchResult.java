package com.battleshippark.grassimage.presentation;

import java.util.List;

/**
 */

class SearchResult {
    final int total;
    final List<SearchResultItem> items;

    SearchResult(int total, List<SearchResultItem> items) {
        this.total = total;
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchResult that = (SearchResult) o;

        if (total != that.total) return false;
        return items != null ? items.equals(that.items) : that.items == null;

    }
}
