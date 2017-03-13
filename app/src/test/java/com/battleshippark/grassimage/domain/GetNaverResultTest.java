package com.battleshippark.grassimage.domain;

import com.battleshippark.grassimage.BuildConfig;
import com.battleshippark.grassimage.data.ReposNaverResultItem;
import com.battleshippark.grassimage.data.NaverSearchInteractor;
import com.battleshippark.grassimage.data.ReposNaverResult;

import org.junit.Test;

import java.util.Arrays;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 */
public class GetNaverResultTest {

    @Test
    public void execute() throws Exception {
        final ReposNaverResult reposNaverResult = new ReposNaverResult(2,
                Arrays.asList(new ReposNaverResultItem("title1", "thumb1", "sizew1", "sizeh1"),
                        new ReposNaverResultItem("title2", "thumb2", "sizew2", "sizeh2"))
        );
        final NaverSearchInteractor interactor = (clientId, clientSecret, query) -> Observable.just(reposNaverResult);
        final GetNaverResult.Param param = new GetNaverResult.Param(null, null, null);
        final GetNaverResult getNaverResult = new GetNaverResult(interactor, param, Schedulers.io(), Schedulers.io(), new NaverMapper());
        final TestSubscriber<DomainResult> subscriber = new TestSubscriber<>();


        getNaverResult.execute(subscriber);


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