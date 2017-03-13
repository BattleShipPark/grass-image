package com.battleshippark.grassimage.data;

import android.util.Log;

import com.battleshippark.grassimage.BuildConfig;

import org.junit.Test;

import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;


/**
 */
public class NaverSearchRepositoryTest {
    @Test
    public void query() throws Exception {
        TestSubscriber<NaverResult> subscriber = new TestSubscriber<>();
        NaverSearchInteractor interactor = new NaverSearchRepository();
        interactor.query(BuildConfig.NAVER_CLIENT_ID, BuildConfig.NAVER_CLIENT_SECRET, "jandi")
                .subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(subscriber);
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
        subscriber.assertCompleted();

        NaverResult result = subscriber.getOnNextEvents().get(0);
        Log.i("Test", result.toString());
    }
}