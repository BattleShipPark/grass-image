package com.battleshippark.grassimage.data;

import android.support.annotation.VisibleForTesting;

import java.util.List;

/**
 */

public class ReposDaumResultChannel {
    public int result;
    public List<ReposDaumResultItem> item;

    @VisibleForTesting
    public ReposDaumResultChannel(int result, List<ReposDaumResultItem> item) {
        this.result = result;
        this.item = item;
    }

    @Override
    public String toString() {
        return "ReposDaumResultChannel{" +
                "result=" + result +
                ", item=" + item +
                '}';
    }
}
