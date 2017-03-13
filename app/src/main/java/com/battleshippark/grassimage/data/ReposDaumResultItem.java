package com.battleshippark.grassimage.data;

import android.support.annotation.VisibleForTesting;

/**
 */

public class ReposDaumResultItem {
    public String title;
    public String image;
    public String thumbnail;
    public String width, height;

    @VisibleForTesting
    public ReposDaumResultItem(String title, String image, String thumbnail, String width, String height) {
        this.title = title;
        this.image = image;
        this.thumbnail = thumbnail;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "ReposDaumResultItem{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
