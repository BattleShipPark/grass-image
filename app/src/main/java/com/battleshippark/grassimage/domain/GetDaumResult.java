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
    public void execute(Subscriber<DomainResult> subscriber) {
        interactor.query(param.apikey, param.query)
                .subscribeOn(scheduler).observeOn(postScheduler).subscribe(reposDaumResult -> {
            subscriber.onNext(mapper.from(reposDaumResult));
            subscriber.onCompleted();
        });
    }

    public static class Param {
        String apikey;
        String query;

        public Param(String apikey, String query) {
            this.apikey = apikey;
            this.query = query;
        }
    }
}
