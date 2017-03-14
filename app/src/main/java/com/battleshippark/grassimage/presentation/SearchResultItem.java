package com.battleshippark.grassimage.presentation;

/**
 */

class SearchResultItem {
    String title;
    String thumbnail;

    SearchResultItem(String title, String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchResultItem that = (SearchResultItem) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return thumbnail != null ? thumbnail.equals(that.thumbnail) : that.thumbnail == null;

    }
}
