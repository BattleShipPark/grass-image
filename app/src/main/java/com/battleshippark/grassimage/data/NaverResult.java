package com.battleshippark.grassimage.data;

import java.util.List;

/**
 */

class NaverResult {
    int total;
    List<NaverResultItem> items;

    @Override
    public String toString() {
        return "NaverResult{" +
                "total=" + total +
                ", item=s" + items +
                '}';
    }
}
