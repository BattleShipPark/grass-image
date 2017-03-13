package com.battleshippark.grassimage.data;

import android.support.annotation.VisibleForTesting;

import java.util.List;

/**
 */

public class DaumResultChannel {
    public int result;
    public List<DaumResultItem> item;

    @VisibleForTesting
    public DaumResultChannel(int result, List<DaumResultItem> item) {
        this.result = result;
        this.item = item;
    }

    @Override
    public String toString() {
        return "DaumResultChannel{" +
                "result=" + result +
                ", item=" + item +
                '}';
    }
}
