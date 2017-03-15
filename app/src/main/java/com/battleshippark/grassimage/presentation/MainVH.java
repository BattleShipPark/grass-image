package com.battleshippark.grassimage.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.battleshippark.grassimage.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */

class MainVH extends RecyclerView.ViewHolder {
    @BindView(R.id.image)
    ImageView imageView;

    MainVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(SearchResultItem searchResultItem) {
        Glide.with(itemView.getContext()).load(searchResultItem.thumbnail).fitCenter()
                .into(imageView);
    }
}
