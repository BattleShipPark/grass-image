package com.battleshippark.grassimage.domain;

import com.battleshippark.grassimage.data.NaverSearchInteractor;

import rx.Scheduler;
import rx.Subscriber;

/**
 */

public class GetNaverResult implements UseCase<GetNaverResult.Param, DomainResult> {
    private final NaverSearchInteractor interactor;
    private final Scheduler scheduler;
    private final Scheduler postScheduler;
    private final NaverMapper naverMapper;

    public GetNaverResult(NaverSearchInteractor interactor, Scheduler scheduler, Scheduler postScheduler, NaverMapper naverMapper) {
        this.interactor = interactor;
        this.scheduler = scheduler;
        this.postScheduler = postScheduler;
        this.naverMapper = naverMapper;
    }

    @Override
    public void execute(Param param, Subscriber<DomainResult> subscriber) {
        interactor.query(param.clientId, param.clientSecret, param.query)
                .subscribeOn(scheduler).observeOn(postScheduler).subscribe(reposNaverResult -> {
            subscriber.onNext(naverMapper.from(reposNaverResult));
            subscriber.onCompleted();
        });
    }

    public static class Param {
        String clientId, clientSecret;
        String query;

        public Param(String clientId, String clientSecret, String query) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.query = query;
        }
    }
}
