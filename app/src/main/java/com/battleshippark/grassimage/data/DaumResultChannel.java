package com.battleshippark.grassimage.data;

import java.util.List;

/**
 */

class DaumResultChannel {
    int result;
    List<DaumResultItem> item;

    @Override
    public String toString() {
        return "DaumResultChannel{" +
                "result=" + result +
                ", item=" + item +
                '}';
    }
}
