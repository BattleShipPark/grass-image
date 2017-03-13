package com.battleshippark.grassimage.presentation;

import com.battleshippark.grassimage.domain.DomainResult;
import com.battleshippark.grassimage.domain.UseCase;

import rx.Subscriber;

/**
 */

class MainPresenter {
    private final UiListener uiListener;
    private final UseCase<DomainResult> getNaverResult;
    private final UseCase<DomainResult> getDaumResult;

    MainPresenter(UiListener uiListener, UseCase<DomainResult> getNaverResult,
                  UseCase<DomainResult> getDaumResult) {
        this.uiListener = uiListener;
        this.getNaverResult = getNaverResult;
        this.getDaumResult = getDaumResult;
    }

    void load(Mode mode) {
        switch (mode) {
            case NAVER:
                getNaverResult.execute(new MainSubscriber());
                break;
            case DAUM:
                getDaumResult.execute(new MainSubscriber());
                break;
        }
    }

    enum Mode {NAVER, DAUM}

    class MainSubscriber extends Subscriber<DomainResult> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(DomainResult domainResult) {
        }
    }
}
