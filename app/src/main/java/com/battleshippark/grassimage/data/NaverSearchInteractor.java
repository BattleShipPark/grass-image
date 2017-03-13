package com.battleshippark.grassimage.data;

import rx.Observable;

/**
 */

public interface NaverSearchInteractor {
    Observable<ReposNaverResult> query(String clientId, String clientSecret, String query);
}
