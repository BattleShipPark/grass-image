package com.battleshippark.grassimage.presentation;

/**
 */

interface UiListener {
    void update(SearchResult result);

    void showProgress();

    void hideProgress();

    void showErrorPage();

    void showEmptyPage();

    void hideEmptyPage();
}
