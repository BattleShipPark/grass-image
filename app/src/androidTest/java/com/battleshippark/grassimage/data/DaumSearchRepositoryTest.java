package com.battleshippark.grassimage.data;

import android.util.Log;

import com.battleshippark.grassimage.BuildConfig;

import org.junit.Test;

import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;


/**
 */
public class DaumSearchRepositoryTest {
    @Test
    public void query() throws Exception {
        TestSubscriber<ReposDaumResult> subscriber = new TestSubscriber<>();
        DaumSearchInteractor interactor = new DaumSearchRepository();
        interactor.query(BuildConfig.DAUM_API_KEY, "jandi")
                .subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(subscriber);
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
        subscriber.assertCompleted();

        ReposDaumResult result = subscriber.getOnNextEvents().get(0);
        Log.i("Test", result.toString());
    }
}