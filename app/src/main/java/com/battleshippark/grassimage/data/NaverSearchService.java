package com.battleshippark.grassimage.data;


import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 */

interface NaverSearchService {
    @GET("/v1/search/image.json?&display=10")
    Observable<NaverResult> query(@Header("X-Naver-Client-Id") String clientId,
                                  @Header("X-Naver-Client-Secret") String clientSecret,
                                  @Query("query") String query);
}
