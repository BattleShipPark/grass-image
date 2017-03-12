package com.battleshippark.grassimage.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 */

class NaverSearchRepository implements NaverSearchInteractor {
    @Override
    public Observable<NaverResult> query(String clientId, String clientSecret, String query) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        NaverSearchService service = retrofit.create(NaverSearchService.class);
        return service.query(clientId, clientSecret, query);
    }
}
