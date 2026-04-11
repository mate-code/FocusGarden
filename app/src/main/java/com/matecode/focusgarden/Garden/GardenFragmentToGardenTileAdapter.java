package com.matecode.focusgarden.Garden;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matecode.focusgarden.R;

public class GardenFragmentToGardenTileAdapter extends RecyclerView.Adapter<GardenFragmentToGardenTileAdapter.ViewHolder> {

    private final GardenViewModel data;
    private int ocupatedViewHolders;

    public GardenFragmentToGardenTileAdapter(GardenViewModel data) {
        this.data = data;
        ocupatedViewHolders = 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.garden_tile_item, parent, false);
        return new ViewHolder(view);
    }

    // onBindViewHolder calls when scroll is moving, then deletes old tiles and creating new ones instead at once creating all needed tiles
    // it is very good for memory usage and efficiency
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GardenTileStatusEnum status = data.getGardenMap().get(position).getStatus();

        switch (status) {
            case EMPTY:
                holder.imageView.setImageDrawable(null);
                break;
            case LIVE:
                holder.imageView.setImageResource(android.R.drawable.btn_star_big_on);
                break;
            case DEAD:
                holder.imageView.setImageResource(android.R.drawable.btn_star_big_off);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return data.getSize();
    }
}