package com.battleshippark.grassimage.data;

import java.util.List;

/**
 */

public class ReposNaverResult {
    public int total;
    public List<NaverResultItem> items;

    public ReposNaverResult(int total, List<NaverResultItem> items) {
        this.total = total;
        this.items = items;
    }

    @Override
    public String toString() {
        return "ReposNaverResult{" +
                "total=" + total +
                ", item=s" + items +
                '}';
    }
}
