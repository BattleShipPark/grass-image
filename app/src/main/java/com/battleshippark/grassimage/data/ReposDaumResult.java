package com.battleshippark.grassimage.data;

import android.support.annotation.VisibleForTesting;

/**
 */

public class ReposDaumResult {
    public ReposDaumResultChannel channel;

    @VisibleForTesting
    public ReposDaumResult(ReposDaumResultChannel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "ReposDaumResult{" +
                "channel=" + channel +
                '}';
    }
}
