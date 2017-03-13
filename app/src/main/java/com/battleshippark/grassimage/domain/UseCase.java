package com.battleshippark.grassimage.domain;

import rx.Subscriber;

/**
 */

public interface UseCase<T> {
    void execute(Subscriber<T> subscriber);
}
