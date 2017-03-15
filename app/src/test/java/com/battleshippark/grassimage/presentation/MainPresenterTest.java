package com.battleshippark.grassimage.presentation;

import com.battleshippark.grassimage.domain.DomainResult;
import com.battleshippark.grassimage.domain.DomainResultItem;
import com.battleshippark.grassimage.domain.UseCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import rx.Subscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    @Mock
    UiListener uiListener;

    @Test
    public void load_naver_empty() throws Exception {
        DomainResult domainResult = new DomainResult(0, Collections.EMPTY_LIST);
        UseCase<DomainResult> getNaverResult = (query, subscriber) -> {
            subscriber.onNext(domainResult);
            subscriber.onCompleted();
        };
        Mapper mapper = new Mapper();
        MainPresenter presenter = new MainPresenter(uiListener, getNaverResult, null, mapper);
        presenter.load(MainPresenter.Mode.NAVER, "");

        InOrder inOrder = inOrder(uiListener);
        inOrder.verify(uiListener).showEmptyPage();
        inOrder.verify(uiListener).hideProgress();
    }

    @Test
    public void load_naver() throws Exception {
        DomainResult domainResult = new DomainResult(2,
                Arrays.asList(new DomainResultItem("title1", "thumb1"),
                        new DomainResultItem("title2", "thumb2"))
        );
        UseCase<DomainResult> getNaverResult = (query, subscriber) -> {
            subscriber.onNext(domainResult);
            subscriber.onCompleted();
        };
        Mapper mapper = new Mapper();
        MainPresenter presenter = new MainPresenter(uiListener, getNaverResult, null, mapper);
        presenter.load(MainPresenter.Mode.NAVER, "");

        InOrder inOrder = inOrder(uiListener);
        inOrder.verify(uiListener).hideEmptyPage();
        inOrder.verify(uiListener).update(mapper.from(domainResult));
        inOrder.verify(uiListener).hideProgress();
    }

    @Test
    public void onQueryChanged_emptyToNonEmpty_inShortTerm() throws InterruptedException {
        MainPresenter presenter = new MainPresenter(uiListener, null, null, null);
        presenter.init();


        presenter.onQueryChanged("nonEmpty");


        Thread.sleep(500);
        verify(uiListener, never()).load();
    }

    @Test
    public void onQueryChanged_emptyToNonEmpty_inEnoughTerm() throws InterruptedException {
        MainPresenter presenter = new MainPresenter(uiListener, null, null, null);
        presenter.init();


        presenter.onQueryChanged("nonEmpty");


        Thread.sleep(1100);
        verify(uiListener).load();
    }
}