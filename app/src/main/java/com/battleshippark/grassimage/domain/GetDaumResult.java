package com.battleshippark.grassimage.domain;

import com.battleshippark.grassimage.data.DaumSearchInteractor;

import rx.Scheduler;
import rx.Subscriber;

/**
 */

public class GetDaumResult implements UseCase<DomainResult> {
    private final DaumSearchInteractor interactor;
    private final Param param;
    private final Scheduler scheduler;
    private final Scheduler postScheduler;
    private final DaumMapper mapper;

    public GetDaumResult(DaumSearchInteractor interactor, Param param, Scheduler scheduler, Scheduler postScheduler, DaumMapper mapper) {
        this.interactor = interactor;
        this.param = param;
        this.scheduler = scheduler;
        this.postScheduler = postScheduler;
        this.mapper = mapper;
    }

    @Override
    public void execute(String query, Subscriber<DomainResult> subscriber) {
        interactor.query(param.apikey, query)
                .subscribeOn(scheduler).observeOn(postScheduler).subscribe(reposDaumResult -> {
            subscriber.onNext(mapper.from(reposDaumResult));
            subscriber.onCompleted();
        }, subscriber::onError);
    }

    public static class Param {
        String apikey;

        public Param(String apikey) {
            this.apikey = apikey;
        }
    }
}
