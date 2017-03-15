package com.battleshippark.grassimage.presentation;

import com.battleshippark.grassimage.OperatorThrottleLastAfterPeriod;
import com.battleshippark.grassimage.domain.DomainResult;
import com.battleshippark.grassimage.domain.UseCase;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 */

class MainPresenter {
    private final UiListener uiListener;
    private final UseCase<DomainResult> getNaverResult;
    private final UseCase<DomainResult> getDaumResult;
    private final Mapper mapper;

    private final PublishSubject<String> queryChanged = PublishSubject.create();
    private Subscription subsc;

    MainPresenter(UiListener uiListener, UseCase<DomainResult> getNaverResult,
                  UseCase<DomainResult> getDaumResult, Mapper mapper) {
        this.uiListener = uiListener;
        this.getNaverResult = getNaverResult;
        this.getDaumResult = getDaumResult;
        this.mapper = mapper;
    }

    void init() {
        subsc = queryChanged
                .lift(new OperatorThrottleLastAfterPeriod<>(1, TimeUnit.SECONDS, Schedulers.io()))
                .subscribe(query -> uiListener.load());
    }

    void release() {
        if (subsc != null) {
            subsc.unsubscribe();
        }
    }

    void load(Mode mode, String query) {
        uiListener.showProgress();

        switch (mode) {
            case NAVER:
                getNaverResult.execute(query, new MainSubscriber());
                break;
            case DAUM:
                getDaumResult.execute(query, new MainSubscriber());
                break;
        }
    }

    void onQueryChanged(String query) {
        queryChanged.onNext(query);
    }

    enum Mode {NAVER, DAUM}

    class MainSubscriber extends Subscriber<DomainResult> {
        @Override
        public void onCompleted() {
            uiListener.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            uiListener.hideProgress();
            uiListener.hideEmptyPage();
            uiListener.showErrorPage();
        }

        @Override
        public void onNext(DomainResult domainResult) {
            if (domainResult.total == 0) {
                uiListener.showEmptyPage();
            } else {
                uiListener.hideEmptyPage();
                uiListener.update(mapper.from(domainResult));
            }
        }
    }
}
