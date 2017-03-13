package com.battleshippark.grassimage.data;

import rx.Observable;

/**
 */

public interface DaumSearchInteractor {
    Observable<ReposDaumResult> query(String apiKey, String query);
}
