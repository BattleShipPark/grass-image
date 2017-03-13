package com.battleshippark.grassimage.data;

/**
 */

class DaumResultItem {
    String title;
    String image;
    String thumbnail;
    String width, height;

    @Override
    public String toString() {
        return "DaumResultItem{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
