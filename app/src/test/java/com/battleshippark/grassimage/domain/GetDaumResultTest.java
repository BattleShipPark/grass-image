package com.battleshippark.grassimage.domain;

import com.battleshippark.grassimage.data.ReposDaumResultChannel;
import com.battleshippark.grassimage.data.ReposDaumResultItem;
import com.battleshippark.grassimage.data.DaumSearchInteractor;
import com.battleshippark.grassimage.data.ReposDaumResult;

import org.junit.Test;

import java.util.Arrays;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.filter;


/**
 */
public class GetDaumResultTest {
    @Test
    public void execute() throws Exception {
        final ReposDaumResult reposDaumResult = new ReposDaumResult(
                new ReposDaumResultChannel(2,
                        Arrays.asList(new ReposDaumResultItem("title1", "image1", "thumb1", "sizew1", "sizeh1"),
                                new ReposDaumResultItem("title2", "image2", "thumb2", "sizew2", "sizeh2")))
        );
        final DaumSearchInteractor interactor = (apikey, query) -> Observable.just(reposDaumResult);
        final GetDaumResult.Param param = new GetDaumResult.Param(null, null);
        final GetDaumResult getDaumResult = new GetDaumResult(interactor, param, Schedulers.io(), Schedulers.io(), new DaumMapper());
        final TestSubscriber<DomainResult> subscriber = new TestSubscriber<>();


        getDaumResult.execute(subscriber);


        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
        subscriber.assertCompleted();

        assertThat(subscriber.getOnNextEvents()).hasSize(1);
        final DomainResult domainResult = subscriber.getOnNextEvents().get(0);
        assertThat(domainResult.total).isEqualTo(2);
        assertThat(domainResult.items).hasSize(2);
        assertThat(domainResult.items.get(0).title).isEqualTo("title1");
        assertThat(domainResult.items.get(0).thumbnail).isEqualTo("thumb1");
        assertThat(domainResult.items.get(1).title).isEqualTo("title2");
        assertThat(domainResult.items.get(1).thumbnail).isEqualTo("thumb2");
    }

}