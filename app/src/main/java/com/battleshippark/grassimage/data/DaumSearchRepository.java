package com.battleshippark.grassimage.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 */

class DaumSearchRepository implements DaumSearchInteractor {
    @Override
    public Observable<DaumResult> query(String apikey, String query) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.daum.net")
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        DaumSearchService service = retrofit.create(DaumSearchService.class);
        return service.query(apikey, query);
    }
}
