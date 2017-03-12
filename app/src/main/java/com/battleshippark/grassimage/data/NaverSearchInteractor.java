package com.battleshippark.grassimage.data;

import rx.Observable;

/**
 */

public interface NaverSearchInteractor {
    Observable<NaverResult> query(String clientId, String clientSecret, String query);
}
