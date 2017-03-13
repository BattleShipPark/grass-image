package com.battleshippark.grassimage.data;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 */

interface DaumSearchService {
    @GET("/search/image?output=json")
    Observable<ReposDaumResult> query(@Query("apikey") String apikey,
                                      @Query("q") String query);
}
