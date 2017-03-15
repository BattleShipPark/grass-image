/**
 * Copyright 2014 Netflix, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.battleshippark.grassimage;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

/**
 * Returning the last value when there is no value for a period.
 */
public final class OperatorThrottleLastAfterPeriod<T> implements Operator<T, T> {

    final long windowDuration;
    final TimeUnit unit;
    final Scheduler scheduler;
    Subscription subscription;

    public OperatorThrottleLastAfterPeriod(long windowDuration, TimeUnit unit, Scheduler scheduler) {
        this.windowDuration = windowDuration;
        this.unit = unit;
        this.scheduler = scheduler;
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) {
            @Override
            public void onStart() {
                request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(T v) {
                if (subscription != null) {
                    subscription.unsubscribe();
                }
                subscription = Observable.timer(windowDuration, unit, scheduler).subscribe(aLong -> onTimerNext(v));
            }

            void onTimerNext(T v) {
                subscriber.onNext(v);
            }

            @Override
            public void onCompleted() {
                subscription.unsubscribe();
                subscriber.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                subscription.unsubscribe();
                subscriber.onError(e);
            }

        };
    }
}
