package com.battleshippark.grassimage.data;

import rx.Observable;

/**
 */

public interface DaumSearchInteractor {
    Observable<DaumResult> query(String apiKey, String query);
}
