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
    private final Mapper mapper;

    MainPresenter(UiListener uiListener, UseCase<DomainResult> getNaverResult,
                  UseCase<DomainResult> getDaumResult, Mapper mapper) {
        this.uiListener = uiListener;
        this.getNaverResult = getNaverResult;
        this.getDaumResult = getDaumResult;
        this.mapper = mapper;
    }

    void load(Mode mode) {
        uiListener.showProgress();

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
