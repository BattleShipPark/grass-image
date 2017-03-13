package com.battleshippark.grassimage.data;

import android.support.annotation.VisibleForTesting;

/**
 */

public class ReposDaumResult {
    public DaumResultChannel channel;

    @VisibleForTesting
    public ReposDaumResult(DaumResultChannel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "ReposDaumResult{" +
                "channel=" + channel +
                '}';
    }
}
