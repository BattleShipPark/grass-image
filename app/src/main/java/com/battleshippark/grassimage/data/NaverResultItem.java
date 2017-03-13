package com.battleshippark.grassimage.data;

import android.support.annotation.VisibleForTesting;

/**
 */

public class NaverResultItem {
    public String title;
    public String thumbnail;
    public String sizewidth, sizeheight;

    @VisibleForTesting
    public NaverResultItem(String title, String thumbnail, String sizewidth, String sizeheight) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.sizewidth = sizewidth;
        this.sizeheight = sizeheight;
    }

    @Override
    public String toString() {
        return "NaverResultItem{" +
                "title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", sizewidth='" + sizewidth + '\'' +
                ", sizeheight='" + sizeheight + '\'' +
                '}';
    }
}
