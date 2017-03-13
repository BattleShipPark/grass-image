package com.battleshippark.grassimage.data;

/**
 */

class NaverResultItem {
    String title;
    String link;
    String thumbnail;
    String sizeheight, sizewidth;

    @Override
    public String toString() {
        return "NaverResultItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", sizeheight='" + sizeheight + '\'' +
                ", sizewidth='" + sizewidth + '\'' +
                '}';
    }
}
