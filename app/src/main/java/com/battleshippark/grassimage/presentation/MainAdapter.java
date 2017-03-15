package com.battleshippark.grassimage.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.battleshippark.grassimage.R;

import java.util.ArrayList;
import java.util.List;

/**
 */

class MainAdapter extends RecyclerView.Adapter<MainVH> {
    private List<SearchResultItem> items = new ArrayList<>();
    private LayoutInflater inflater;

    MainAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.listitem_main, parent, false);
        return new MainVH(view);
    }

    @Override
    public void onBindViewHolder(MainVH holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void setItems(List<SearchResultItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
